package org.oop;

public class Article {
    // Нарушен принцип KISS: эти поля следовало сделать private
    public Long id;
    public String title;
    public String content;
    public Long authorId;

    // Конструктор по умолчанию
    // YAGNI: в данном коде этот конструктор нигде не используется
    public Article() {
    }

    // Конструктор с параметрами
    public Article(Long id, String title, String content, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Заголовок: " + title + "\nСодержимое: " + content;
    }
}
