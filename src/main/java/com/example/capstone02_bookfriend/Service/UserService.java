package com.example.capstone02_bookfriend.Service;

import com.example.capstone02_bookfriend.Model.*;
import com.example.capstone02_bookfriend.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final OrderRepository orderRepository;
    private final JoinRepository joinRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Boolean addUser(User user) {
        User checkEmail = userRepository.findUserByEmail(user.getEmail());
        if (checkEmail != null)
            return false;
        userRepository.save(user);
        return true;
    }

    public Boolean updateUser(Integer id, User user) {
        User checkEmail = userRepository.findUserByEmail(user.getEmail());
        User oldUser = userRepository.findUserById(id);
        if (checkEmail != null || oldUser == null)
            return false;

        oldUser.setBalance(user.getBalance());
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null)
            return false;
        userRepository.delete(user);
        return true;
    }

    // endpoint 1
    public Boolean joinGroup(Integer id, Integer group_id) {
        Groups groups = groupRepository.findGroupById(group_id);
        User user = userRepository.findUserById(id);
        Joins joins = new Joins();
        Joins checkJoin = joinRepository.findJoinsByGroup_idAndUser_id(group_id,id);
        if (groups == null || user == null )
            return false;
        else {
            if (checkJoin!=null)
                if (checkJoin.getState().equals("joined"))
                    return false;
            if (groups.getMax_capacity() == groups.getNumber_of_users())
                return false;

        }

        joins.setUser_id(id);
        joins.setGroup_id(group_id);
        joins.setState("joined");
        groups.setNumber_of_users(groups.getNumber_of_users() + 1);
        user.setGroup_id(group_id);
        if (checkJoin!=null){
            checkJoin.setState("joined");
            joinRepository.save(checkJoin);
        }else
            joinRepository.save(joins);
        groupRepository.save(groups);
        userRepository.save(user);
        return true;
    }

    // endpoint 2
    public String purchaseBook(Integer id, Integer book_id) {
        Book book = bookRepository.findBooksById(book_id);
        User user = userRepository.findUserById(id);
        Orders orders = new Orders();

        if (book == null || user == null)
            return "not found";
        double totalPrice = (book.getPrice() * 0.15) + book.getPrice();
        if (user.getBalance() < totalPrice)
            return "price";
        if (book.getStock() == 0)
            return "stock";
        user.setBalance(user.getBalance() - totalPrice);
        book.setStock(book.getStock() - 1);
        orders.setTotal_price(totalPrice);
        orders.setBook_id(book_id);
        orders.setUser_id(id);
        orders.setState("completed");
        orderRepository.save(orders);
        userRepository.save(user);
        bookRepository.save(book);
        return "purchased";
    }

    // endpoint 3
    public Boolean returnBook(Integer id, Integer order_id) {
        User user = userRepository.findUserById(id);
        Orders orders = orderRepository.findOrderById(order_id);
        if (user == null)
            return false;
        if (orders != null && orders.getUser_id() == user.getId() && orders.getState().equals("completed")) {
            Book book = bookRepository.findBooksById(orders.getBook_id());
            double price = orders.getTotal_price() - (book.getPrice() * 0.15);
            user.setBalance(user.getBalance() + price);
            book.setStock(book.getStock() + 1);
            orders.setState("returned");
            orderRepository.save(orders);
            userRepository.save(user);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    // endpoint 4
    public Boolean leaveGroup(Integer id, Integer group_id) {
        Groups groups = groupRepository.findGroupById(group_id);
        User user = userRepository.findUserById(id);
        Joins joins = joinRepository.findJoinsByGroup_idAndUser_id(group_id, id);
        if (joins == null||groups == null || user == null||!joins.getState().equals("joined"))
            return false;

        joins.setState("withdrawn");
        groups.setNumber_of_users(groups.getNumber_of_users() - 1);
        user.setGroup_id(null);
        groupRepository.save(groups);
        userRepository.save(user);
        joinRepository.save(joins);
        return true;
    }

}
