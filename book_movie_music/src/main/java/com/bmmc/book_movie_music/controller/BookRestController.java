package com.bmmc.book_movie_music.controller;

import com.bmmc.book_movie_music.pojo.*;
import com.bmmc.book_movie_music.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/findAllBooks")
    public List<AllBooks> findAllBooks(){
        System.out.println("查所有书");
        return bookService.findAllBooks();
    }

    @GetMapping(value = "/findHighPointBooks")
    public List<AllBooks> findHighPointBooks(){
        System.out.println("查高分书");
        return bookService.findHighPointBooks();
    }

    @PostMapping(value = "/findOneBookByid")
    public AllBooks findOneBookByid(@RequestBody AllBooks allBooks){
        System.out.println("查一本书");
        return bookService.findOneBookByid(allBooks.getBookid());
    }

    @PostMapping(value = "/deleteBookByid")
    public boolean deleteBookByid(@RequestBody AllBooks allBooks){
        System.out.println("删除书");
        return bookService.deleteBookByid(allBooks.getBookid());
    }

    @PostMapping(value = "/updateBookByid")
    public boolean updateBookByid(@RequestBody AllBooks allBooks){
        System.out.println("修改书");
        System.out.println(allBooks.getBookid());
        return bookService.updateBookByid(allBooks);
    }

    @PostMapping(value = "/addBooks")
    public boolean addBooks(@RequestBody AllBooks allBooks){
        System.out.println("添加书");
        return bookService.addBooks(allBooks);
    }

    @PostMapping(value = "/findBookBySearch")
    public List<AllBooks> findBookBySearch(@RequestBody AllBooks allBooks){
        System.out.println("模糊查询书");
        return bookService.findBookBySearch(allBooks.getBookname());
    }

    @PostMapping(value = "/findChapterByBookid")
    public List<Chapter> findChapterByBookid(@RequestBody AllBooks allBooks){
        System.out.println("查1书的所有章节");
        return bookService.findChapterByBookid(allBooks.getBookid());
    }

        @PostMapping(value = "/findOneChapterByid")
    public Chapter findOneChapterByid(@RequestBody Chapter chapter){
        System.out.println("查一个章节");
        return bookService.findOneChapterByid(chapter.getChapterid());
    }

    @PostMapping(value = "/deleteChapterByid")
    public boolean deleteChapterByid(@RequestBody Chapter chapter){
        System.out.println("删除章节");
        return bookService.deleteChapterByid(chapter.getChapterid());
    }

    @PostMapping(value = "/updateChapterByid")
    public boolean updateChapterByid(@RequestBody Chapter chapter){
        System.out.println("修改章节");
        System.out.println(chapter.getCtime());
        return bookService.updateChapterByid(chapter);
    }

    @PostMapping(value = "/addChapter")
    public boolean addChapter(@RequestBody Chapter chapter){
        System.out.println("添加章节");
        return bookService.addChapter(chapter);
    }

    @PostMapping(value = "/findChapterBySearch")
    public List<Chapter> findChapterBySearch(@RequestBody Chapter chapter){
        System.out.println("模糊查询章节");
        return bookService.findChapterBySearch(chapter.getTitle());
    }

    @PostMapping(value = "/findBookCommentBybookid")
    public List<BookComment> findBookCommentBybookid(@RequestBody AllBooks allBooks){
        System.out.println("查1书的所有书评");
        return bookService.findBookCommentBybookid(allBooks.getBookid());
    }

    @PostMapping(value = "/findBookCommentByuserid")
    public List<BookComment> findBookCommentByuserid(@RequestBody User user){
        System.out.println("查1用户的所有书评");
        return bookService.findBookCommentByuserid(user.getUserid());
    }

    @PostMapping(value = "/deleteBookComment")
    public boolean deleteBookComment(@RequestBody BookComment bookComment){
        System.out.println("删除书评");
        return bookService.deleteBookComment(bookComment.getBookcommentid());
    }

    @PostMapping(value = "/addBookComment")
    public boolean addChapter(@RequestBody BookComment bookComment){
        System.out.println("添加书评");
        return bookService.addBookComment(bookComment);
    }

    @PostMapping(value = "/getAvgBookPoint")
    public Double getAvgBookPoint(@RequestBody BookComment bookComment){
        System.out.println("求书评平均值");
        return bookService.getAvgBookPoint(bookComment.getBookid());
    }

    @PostMapping(value = "/findBCommentBySearch")
    public List<BookComment> findBCommentBySearch(@RequestBody BookComment bookComment){
        System.out.println("模糊查询书评");
        return bookService.findBCommentBySearch(bookComment.getComment());
    }

    @PostMapping(value = "/bookgreat")
    public boolean bookGreat(@RequestBody BookComment bookComment){
        System.out.println("赞书评");
        return bookService.great(bookComment);
    }

    @PostMapping(value = "/bookstep")
    public boolean bookStep(@RequestBody BookComment bookComment){
        System.out.println("踩书评");
        return bookService.step(bookComment);
    }

    @PostMapping(value = "/findMyBooksUserid")
    public List<WannaRead> findMyBooksUserid(@RequestBody WannaRead wannaRead){
        System.out.println("查我的书架");
        return bookService.findMyBooksUserid(wannaRead.getUserid());
    }

    @PostMapping(value = "/addInBookShelf")
    public boolean addInBookShelf(@RequestBody WannaRead wannaRead){
        System.out.println("加入书架");
        return bookService.addInBookShelf(wannaRead);
    }

    @PostMapping(value = "/deleteBookInShelf")
    public boolean deleteBookInShelf(@RequestBody WannaRead wannaRead){
        System.out.println("书架删除书");
        return bookService.deleteBookInShelf(wannaRead.getBookid());
    }
}
