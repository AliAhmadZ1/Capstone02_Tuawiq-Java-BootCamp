package com.example.capstone02_bookfriend.Service;

import com.example.capstone02_bookfriend.Model.Publisher;
import com.example.capstone02_bookfriend.Repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Boolean addPublisher(Publisher publisher) {
        Publisher existPublisher = publisherRepository.findPublisherByUser_id(publisher.getUser_id());
        if (existPublisher != null)
            return false;
        publisherRepository.save(publisher);
        return true;
    }

    public Boolean updatePublisher(Integer id, Publisher publisher) {
        Publisher existPublisher = publisherRepository.findPublisherByUser_id(publisher.getUser_id());
        Publisher oldPublisher = publisherRepository.findPublisherById(id);
        if (existPublisher != null || oldPublisher == null)
            return false;

        oldPublisher.setType(publisher.getType());
        oldPublisher.setUser_id(publisher.getUser_id());
        publisherRepository.save(oldPublisher);
        return true;
    }

    public Boolean deletePublisher(Integer id) {
        Publisher publisher = publisherRepository.findPublisherById(id);
        if (publisher == null)
            return false;
        publisherRepository.delete(publisher);
        return true;
    }

}
