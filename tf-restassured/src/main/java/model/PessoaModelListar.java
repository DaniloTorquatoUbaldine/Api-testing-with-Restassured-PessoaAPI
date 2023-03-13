package model;

import java.util.ArrayList;

public class PessoaModelListar {

    private String totalElements;
    private String totalPages;
    private String page;
    private String size;
    private ArrayList<PessoaModel> content;

    public PessoaModelListar() {
    }

    public PessoaModelListar(String totalElements, String totalPages, String page, String size, ArrayList<PessoaModel> content) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.page = page;
        this.size = size;
        this.content = content;
    }

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ArrayList<PessoaModel> getContent() {
        return content;
    }

    public void setContent(ArrayList<PessoaModel> content) {
        this.content = content;
    }
}
