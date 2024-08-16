import java.util.List;

public class ResultsProcessor {

    private final int group1Points = 1;

    private final int group2Points = 2;

    private final int group3Points = 3;

    public int calculateTotalPoints(String keyFile, String studentFile) {
        KeyFileParser keyFileParser = new KeyFileParser();
        StudentFileParser studentFileParser = new StudentFileParser();

        int totalPoints = 0;

        // Парсинг файлов с ключами и ответами ученика
        List<String> keyAnswers = keyFileParser.parseFile(keyFile);
        List<String> studentAnswers = studentFileParser.parseFile(studentFile);

        // Подсчет баллов за каждый вопрос
        for (int i = 0; i < keyAnswers.size(); i++) {
            int answerIndex = keyAnswers.get(i).length() - 1;
            if (i < 4) {
                if (keyAnswers.get(i).substring(answerIndex).equals(studentAnswers.get(i).substring(answerIndex))) {
                    totalPoints += group1Points;
                }
            } else if (i < 8) {
                if (keyAnswers.get(i).substring(answerIndex).equals(studentAnswers.get(i).substring(answerIndex))) {
                    totalPoints += group2Points;
                }
            } else {
                if (keyAnswers.get(i).substring(answerIndex).equals(studentAnswers.get(i).substring(answerIndex))) {
                    totalPoints += group3Points;
                }
            }
        }

        return totalPoints;
    }
}
