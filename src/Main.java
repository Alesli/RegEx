import config.FileConfig;
import reader_writer.ReadFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by zf on 06.06.2019.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String inform = new ReadFile().readFiles(FileConfig.FILE_NAME);
        System.out.println(inform);

//      второй вариант чтения файла
//        try {
//            String con = new ReadFile().readFile(FileConfig.FILE_NAME);
//            System.out.println(con);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("++++++++++++++");
        Text text = new Text();
        List<Sentence> sentenceList = text.parseText(inform);
        iterator(sentenceList);

        /*Во всех вопросительных предложениях преобразовывается каждое слово,
         * удаляется из него все последующие вхождения первой буквы этого слова.*/
        System.out.println("+++++++ Задание 1 +++++++");
        List<Sentence> sentenceL = text.getInterrogativeSent();
        iterator(sentenceL);

        /*Найти все слова в первом предложении, которых нет ни в одном из
        остальных предложений.*/
        System.out.println("++++++ Задание 2 ++++++++");
        List<String> stringList = text.getAllUnrepeatWords();
        for (String s : stringList) {
            System.out.print(s + " ");
        }

        /*В каждом восклицательном предложении текста поменять местами
        первое слово с последним.*/
        System.out.println();
        System.out.println("++++++ Задание 3 ++++++++");
        List<Sentence> sentenceList1 = text.getExclamatorySent();
        iterator(sentenceList1);

        /*В каждом повествовательном предложении текста исключить подстроку
        максимальной длины, начинающуюся и заканчивающуюся заданными
        символами.*/
        System.out.println("++++++ Задание 4 ++++++++");
        List<Sentence> sentenceList2 = text.getDeclarativeSent('а', 'в');
        iterator(sentenceList2);

        /*Определяет есть ли в предложении заданное слово*/
        System.out.println("++++++++++++++");
        text.getNecessaryWord("ожиданий");
    }

    private static void iterator(List<Sentence> list) {
        for (Sentence sentence : list) {
            System.out.println(sentence);
        }
    }
}
