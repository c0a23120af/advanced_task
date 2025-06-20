package pnw.common;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

// /question/QuestionsServlet でアクセスできるように設定
@WebServlet("/question/QuestionsServlet")
public class QuestionsServlet extends HttpServlet {
    public static class Choice {
        public int choicesId;
        public String choiceText;
        public boolean isCorrect;
    }

    public static class Quiz {
        public int quizId;
        public String imagePath;
        public String commentary;
        public List<Choice> choices = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Quiz> quizList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/2025n?useUnicode=true&characterEncoding=UTF-8";
            String user = "2025n";
            String password = "2025n";
            Connection conn = DriverManager.getConnection(url, user, password);

            // まず全クイズを取得
            String sqlQuiz = "SELECT quiz_id, image_path, commentary FROM quizzes";
            PreparedStatement psQuiz = conn.prepareStatement(sqlQuiz);
            ResultSet rsQuiz = psQuiz.executeQuery();
            Map<Integer, Quiz> quizMap = new HashMap<>();
            while (rsQuiz.next()) {
                Quiz quiz = new Quiz();
                quiz.quizId = rsQuiz.getInt("quiz_id");
                quiz.imagePath = rsQuiz.getString("image_path");
                quiz.commentary = rsQuiz.getString("commentary");
                quizList.add(quiz);
                quizMap.put(quiz.quizId, quiz);
            }
            rsQuiz.close();
            psQuiz.close();

            // 次に全選択肢を取得し、該当クイズに紐付け
            String sqlChoice = "SELECT choices_id, choice_text, is_correct, quiz_id FROM choices";
            PreparedStatement psChoice = conn.prepareStatement(sqlChoice);
            ResultSet rsChoice = psChoice.executeQuery();
            while (rsChoice.next()) {
                int quizId = rsChoice.getInt("quiz_id");
                Quiz quiz = quizMap.get(quizId);
                if (quiz != null) {
                    Choice choice = new Choice();
                    choice.choicesId = rsChoice.getInt("choices_id");
                    choice.choiceText = rsChoice.getString("choice_text");
                    choice.isCorrect = rsChoice.getInt("is_correct") == 1;
                    quiz.choices.add(choice);
                }
            }
            rsChoice.close();
            psChoice.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("quizList", quizList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/top/questions.jsp");
        dispatcher.forward(request, response);
    }
}
