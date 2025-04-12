package com.example.capstone02_bookfriend.Controller;

import com.example.capstone02_bookfriend.ApiResponse.ApiResponse;
import com.example.capstone02_bookfriend.Model.User;
import com.example.capstone02_bookfriend.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        if (userService.getAllUsers().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no users"));
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isAdded = userService.addUser(user);
        if (isAdded)
            return ResponseEntity.status(200).body(new ApiResponse("new user is added"));
        return ResponseEntity.status(400).body(new ApiResponse("email is already exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody@Valid User user, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not found or email is already used"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    // endpoint 1
    @PutMapping("/join/{id},{group_id}")
    public ResponseEntity joinGroup(@PathVariable Integer id, @PathVariable Integer group_id){
        boolean isJoined = userService.joinGroup(id, group_id);
        if (isJoined)
            return ResponseEntity.status(200).body(new ApiResponse("you are joined successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("this user or group not found"));
    }

    // endpoint 2
    @PostMapping("/purchase/{id},{book_id}")
    public ResponseEntity purchaseBook(@PathVariable Integer id,@PathVariable Integer book_id){
        String purchased = userService.purchaseBook(id, book_id);
        if (purchased.equals("not found"))
            return ResponseEntity.status(400).body(new ApiResponse("user or book not found"));
        if (purchased.equals("price"))
            return ResponseEntity.status(400).body(new ApiResponse("your balance is not enough for this price"));
        if (purchased.equals("stock"))
            return ResponseEntity.status(400).body(new ApiResponse("book is out of stock"));
        return ResponseEntity.status(200).body(new ApiResponse("purchased is done"));
    }

}
