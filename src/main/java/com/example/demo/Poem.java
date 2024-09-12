package com.example.demo;

import java.util.List;

// A java class designed to hold a poem from the PoetryDB API
public class Poem {
    public String name;
    public String author;
    public List<String> lines;
    public int linecount;

    //General constructor, takes the name, author, lines and linecount
    public Poem(String nName, String nAuthor, List<String> nLines, int nLinecount){
        this.name = nName;
        this.author = nAuthor;
        this.lines = nLines;
        this.linecount = nLinecount;
    }


    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getLines() {
        return lines;
    }

    public int getLinecount() {
        return linecount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void setLinecount(int linecount) {
        this.linecount = linecount;
    }
}
