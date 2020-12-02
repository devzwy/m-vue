package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Book;

/**
 * 书Service接口
 * 
 * @author Jason
 * @date 2020-12-02
 */
public interface IBookService 
{
    /**
     * 查询书
     * 
     * @param id 书ID
     * @return 书
     */
    public Book selectBookById(Long id);

    /**
     * 查询书列表
     * 
     * @param book 书
     * @return 书集合
     */
    public List<Book> selectBookList(Book book);

    /**
     * 新增书
     * 
     * @param book 书
     * @return 结果
     */
    public int insertBook(Book book);

    /**
     * 修改书
     * 
     * @param book 书
     * @return 结果
     */
    public int updateBook(Book book);

    /**
     * 批量删除书
     * 
     * @param ids 需要删除的书ID
     * @return 结果
     */
    public int deleteBookByIds(Long[] ids);

    /**
     * 删除书信息
     * 
     * @param id 书ID
     * @return 结果
     */
    public int deleteBookById(Long id);
}
