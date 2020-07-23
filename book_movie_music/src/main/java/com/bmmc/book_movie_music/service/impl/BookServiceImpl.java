package com.bmmc.book_movie_music.service.impl;

import com.bmmc.book_movie_music.dao.BookDao;
import com.bmmc.book_movie_music.pojo.AllBooks;
import com.bmmc.book_movie_music.pojo.BookComment;
import com.bmmc.book_movie_music.pojo.Chapter;
import com.bmmc.book_movie_music.pojo.WannaRead;
import com.bmmc.book_movie_music.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public List<AllBooks> findAllBooks() {
        return bookDao.findAllBooks();
    }

    @Override
    public List<AllBooks> findBookByType(int book_typeid) {
        return bookDao.findBookByType(book_typeid);
    }

    @Override
    public List<AllBooks> findBookByName(String bookname) {
        return bookDao.findBookByName(bookname);
    }

    @Override
    public List<AllBooks> findHighPointBooks() {
        return bookDao.findHighPointBooks();
    }

    @Override
    public boolean deleteBookByid(int bookid) {
        boolean flag = false;
        try {
            bookDao.deleteBookByid(bookid);
            flag = true;
        }catch (Exception e){
            System.out.println("删除书byid失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addBooks(AllBooks allBooks) {
        boolean flag = false;
        try {
            bookDao.addBooks(allBooks);
            flag = true;
        }catch (Exception e){
            System.out.println("添加书失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Chapter> findAllChapter() {
        return bookDao.findAllChapter();
    }

    @Override
    public List<Chapter> findChapterByBookid(int bookid) {
        return bookDao.findChapterByBookid(bookid);
    }

    @Override
    public List<Chapter> findChapterBySearch(String chapterSearch) {
        return bookDao.findChapterBySearch(chapterSearch);
    }

    @Override
    public boolean deleteChapterByid(int chapterid) {
        boolean flag = false;
        try {
            bookDao.deleteChapterByid(chapterid);
            flag = true;
        }catch (Exception e){
            System.out.println("删除章节byid失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateChapterIsDel(int chapterid) {
        boolean flag = false;
        try {
            bookDao.updateChapterIsDel(chapterid);
            flag = true;
        }catch (Exception e){
            System.out.println("删除章节byid失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addChapter(Chapter chapter) {
        boolean flag = false;
        try {
            bookDao.addChapter(chapter);
            flag = true;
        }catch (Exception e){
            System.out.println("添加章节");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateChapterByid(Chapter chapter) {
        boolean flag = false;
        try {
            bookDao.updateChapterByid(chapter);
            flag = true;
        }catch (Exception e){
            System.out.println("修改章节信息");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<BookComment> findAllBookComment() {
        return bookDao.findAllBookComment();
    }

    @Override
    public List<BookComment> findBookCommentByuserid(int userid) {
        return bookDao.findBookCommentByuserid(userid);
    }

    @Override
    public List<BookComment> findBookCommentBybookid(int bookid) {
        return bookDao.findBookCommentBybookid(bookid);
    }

    @Override
    public boolean deleteBookComment(int bookcommentid) {
        boolean flag = false;
        try {
            bookDao.deleteBookComment(bookcommentid);
            flag = true;
        }catch (Exception e){
            System.out.println("删除书评byid");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateBookByid(AllBooks allBooks) {
        boolean flag = false;
        try {
            bookDao.updateBookByid(allBooks);
            flag = true;
        }catch (Exception e){
            System.out.println("修改书信息失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<AllBooks> findBookBySearch(String bookSearch) {
        return bookDao.findBookBySearch(bookSearch);
    }

    @Override
    public AllBooks findOneBookByid(int bookid) {
        return bookDao.findOneBookByid(bookid);
    }

    @Override
    public Chapter findOneChapterByid(int chapterid) {
        return bookDao.findOneChapterByid(chapterid);
    }

    @Override
    public List<BookComment> findBCommentBySearch(String bcommentSearch) {
        return bookDao.findBCommentBySearch(bcommentSearch);
    }

    @Override
    public boolean addBookComment(BookComment bookComment) {
        boolean flag = false;
        try {
            bookDao.addBookComment(bookComment);
            flag = true;
        }catch (Exception e){
            System.out.println("添加书评失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean great(BookComment bookComment) {
        boolean flag = false;
        try {
            bookDao.great(bookComment);
            flag = true;
        }catch (Exception e){
            System.out.println("赞失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean step(BookComment bookComment) {
        boolean flag = false;
        try {
            bookDao.step(bookComment);
            flag = true;
        }catch (Exception e){
            System.out.println("踩失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<WannaRead> findMyBooksUserid(int userid) {
        return bookDao.findMyBooksUserid(userid);
    }

    @Override
    public boolean addInBookShelf(WannaRead wannaRead) {
        boolean flag = false;
        try {
            bookDao.addInBookShelf(wannaRead);
            flag = true;
        }catch (Exception e){
            System.out.println("加入书架失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteBookInShelf(int bookid) {
        boolean flag = false;
        try {
            bookDao.deleteBookInShelf(bookid);
            flag = true;
        }catch (Exception e){
            System.out.println("删除书架的书失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public double getAvgBookPoint(int bookid) {
        return bookDao.getAvgBookPoint(bookid);
    }
}
