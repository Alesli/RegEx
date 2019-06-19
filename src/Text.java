import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zf on 06.06.2019.
 */
public class Text {
    // разбивает текст на предложения
    private static final String TEXT_REGEX = "([А-ЯA-Z]([^?!.\\(]|\\([^\\)]*\\))*[.?!])";
    private List<Sentence> sentences = new ArrayList<>();

    /**
     * Метод разбивает текст на предложения и отправляет их в метод parseSentence()
     *
     * @param texStr текст
     * @return sentences, коллекция предложений
     */
    public List<Sentence> parseText(String texStr) {
        Pattern p1 = Pattern.compile(TEXT_REGEX);
        Matcher m1 = p1.matcher(texStr);
        String ss;
        sentences = new ArrayList<>();
        // пока ищет,записывает данные в новый метод
        while (m1.find()) {
            ss = m1.group().trim();
            if (ss.length() > 0) {
                Sentence sentence = new Sentence(ss);
                sentences.add(sentence);
            }
        }
        return sentences;
    }

    /**
     * Метод, который во всех вопросительных предложениях преобразовывает каждое слово,
     * удаляя из него все последующие вхождения первой буквы этого слова.
     */
    public List<Sentence> getInterrogativeSent() {
        sentences.stream().filter(sentence -> sentence.toString().endsWith("?")).forEach(Sentence::changeWord);
        return sentences;
    }

    /**
     * Метод, который находит все слова в первом предложении, которых нет ни в одном из
     * остальных предложений.
     */
    public List<String> getAllUnrepeatWords() {
        String firstSentence = sentences.get(0).toString()
                .replaceAll(Sentence.PUNCTUATION_REGEX, "").toLowerCase();
        String[] firstSent = firstSentence.split(Sentence.SENTENCE_REGEX);
        String otherSentences = sentences.toString()
                .replaceAll(sentences.get(0).toString(), "")
                .replaceAll(Sentence.PUNCTUATION_REGEX, "").toLowerCase();

        List<String> result = new ArrayList<>();
        for (String s : firstSent) {
            if (!otherSentences.contains(s)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Метод, который в каждом восклицательном предложении текста меняет местами
     * первое слово с последним.
     */
    public List<Sentence> getExclamatorySent() {
        for (Sentence sentence : sentences) {
            if (sentence.toString().endsWith("!")) {
                sentence.replaceFirstToLast();
            }
        }
        return sentences;
    }

    /**
     * Метод, который в каждом повествовательном предложении текста исключает подстроку
     * максимальной длины, начинающуюся и заканчивающуюся заданными
     * символами.
     *
     * @param startElement символ начала подстроки
     * @param endElement   символ конца подстроки
     */
    public List<Sentence> getDeclarativeSent(char startElement, char endElement) {
        sentences.stream().filter(sentence -> sentence.toString().endsWith(".")).forEach(sentence ->
                sentence.replaceChar(startElement, endElement));
        return sentences;
    }

    /**
     * Метод определяет есть ли в тексте заданное слово
     *
     * @param myWord, слово, которое надо найти в тексте
     */
    public void getNecessaryWord(String myWord) {
        for (Sentence sentence : sentences) {
            sentence.getWord(myWord);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sentences.forEach(element -> sb.append(" ").append(element));
        return String.valueOf(sb).trim();
    }
}
