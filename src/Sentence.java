import java.util.ArrayList;
import java.util.List;

/**
 * Created by zf on 06.06.2019.
 */
public class Sentence {

    private ArrayList<Element> elementsList = new ArrayList<>();

    // private static final String SENTENCE_REGEX = "[\\s]|(?=[?!,\\.)\\-])";
    public static final String SENTENCE_REGEX = " +|(?=\\p{Punct})|(?<=\\p{Punct})";
    public static final String PUNCTUATION_REGEX = "[?!,\\.)\\-]";

    public Sentence(String sentence) {
        parseSentence(sentence);
    }

    /**
     * Метод разбивает предложения на слова знаки препинания
     *
     * @param texStr текст
     * @return elementsList, коллекция слов и знаков препинания
     */
    public List<Element> parseSentence(String texStr) {
        String[] a = texStr.split(SENTENCE_REGEX);
        elementsList = new ArrayList<>();
        for (String s : a) {
            if (s.matches(PUNCTUATION_REGEX)) {
                elementsList.add(new PunctMark(s.trim()));
            } else {
                elementsList.add(new Word(s.trim()));
            }
        }
        return elementsList;
    }

    /**
     * Метод удаляет последующее вхождение символа слова.
     */
    public void changeWord() {
        elementsList.stream().filter(element -> element instanceof Word).forEach(element ->
                ((Word) element).removeSimilarLetter());
    }

    /**
     * Метод меняет местами первое и последнее слова
     */
    public List<Element> replaceFirstToLast() {
        Element last = elementsList.get(elementsList.size() - 2);
        Element first = elementsList.get(0);
        elementsList.set(0, last);
        elementsList.set(elementsList.size() - 2, first);
        return elementsList;
    }

    /**
     * Метод исключает подстроку максимальной длины, начинающуюся и заканчивающуюся
     * заданными символами.
     *
     * @param startElement символ начала подстроки
     * @param endElement   символ конца подстроки
     */
    public String replaceChar(char startElement, char endElement) {

        int startEl = this.toString().indexOf(startElement);
        int endEl = this.toString().lastIndexOf(endElement);
        String str = this.toString().substring(startEl, endEl + 1);
        String newStr = this.toString().replaceAll(str, " -- ");
        this.parseSentence(newStr);

        return newStr;
    }

    /**
     * Метод определяет есть ли в тексте заданное слово
     *
     * @param source, слово, которое надо найти в коллекции слов
     */
    public void getWord(String source) {
        elementsList.stream().filter(element -> source.equals(element.toString())).forEach(element ->
                System.out.println("Слово найдено - " + element.toString()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Element element : elementsList) {
            if (element.getSpaceAfterElement()) {
                sb.append(element);
            } else {
                sb.append(" ").append(element);
            }
        }
        return String.valueOf(sb).trim();
    }
}
