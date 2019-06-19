public class PunctMark implements Element{

    private String punctMark;

    public PunctMark(String punctMark) {
        this.punctMark = punctMark;
    }

    @Override
    public String toString() {
        return punctMark;
    }

    @Override
    public boolean getSpaceAfterElement() {
        return true;
    }
}
