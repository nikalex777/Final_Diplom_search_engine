package main.java;

public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final int page;
    private final int count;

    PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    @Override
    public int compareTo(PageEntry pageEntry) {
        return pageEntry.count - this.count; //Выдача в порядке убывания count.
    }

    @Override
    public String toString() {
        return "PageEntry {pdf = " + pdfName + ", page = " + page + ", count = " + count;
    }
}
