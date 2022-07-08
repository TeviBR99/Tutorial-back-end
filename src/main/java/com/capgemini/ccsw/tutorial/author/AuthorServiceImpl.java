package com.capgemini.ccsw.tutorial.author;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.tutorial.author.model.Author;
import com.capgemini.ccsw.tutorial.author.model.AuthorDto;
import com.capgemini.ccsw.tutorial.author.model.AuthorSearchDto;

/**
 * @author ccsw
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author get(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Author> findPage(AuthorSearchDto dto) {
        // select * from Author where Pageable = Pageable
        return this.authorRepository.findAll(dto.getPageable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, AuthorDto data) {

        Author author = null;
        if (id != null)
            author = this.get(id);
        else
            author = new Author();

        BeanUtils.copyProperties(data, author, "id");

        this.authorRepository.save(author);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {

        this.authorRepository.deleteById(id);

    }

    @Override
    public List<Author> findAll() {
        // TODO Auto-generated method stub
        return (List<Author>) this.authorRepository.findAll();
    }

}
