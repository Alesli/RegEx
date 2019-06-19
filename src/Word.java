public class Word implements Element {

    private String word;

    /**
     * Конструктор объека Word.
     *
     * @param word String
     */

    public Word(String word) {
        this.word = word;
    }

    /**
     * Метод удаляет все последующие вхождения первой буквы каждого
     * слова.
     */
    public void removeSimilarLetter() {
        StringBuilder sb = new StringBuilder();
        char firstLetter = word.charAt(0);
        sb.append(firstLetter);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != firstLetter) {
                sb.append(word.charAt(i));
            }
        }
        word = sb.toString();
    }

    /**
     * Метод возвращает строковое представление объекта
     * @return word
     */

    @Override
    public String toString() {
        return word;
    }

    /**
     * Метод определяет наличие пробела после элемента
     * @return false
     */
    @Override
    public boolean getSpaceAfterElement() {
        return false;
    }
}
