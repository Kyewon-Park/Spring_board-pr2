package kye.won.domain;

import lombok.Data;

@Data
public class Article {
    private Long id;
    private String articleName;
    private String textBody;

    public Article() {
    }

    public Article(String articleName, String textBody) {
        this.articleName = articleName;
        this.textBody = textBody;
    }
}
