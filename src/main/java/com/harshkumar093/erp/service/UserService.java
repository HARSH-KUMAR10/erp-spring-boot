package com.harshkumar093.erp.service;

import com.harshkumar093.erp.model.UserModel;
import com.harshkumar093.erp.repository.UserRepository;
import com.harshkumar093.erp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceOps<UserModel>{
    @Autowired
    private UserRepository userRepository;


    @Override
    public Response<Long> create(UserModel userModel) {
        userRepository.save(userModel);
        return new Response<Long>(200,"created user",userModel.getUserId());
    }

    @Override
    public Response<List<UserModel>> readAll() {
        return new Response<List<UserModel>>(200,
                "fetched all users successfully",
                userRepository.findAll());
    }

    @Override
    public Response<UserModel> read(long id) {
        return new Response<UserModel>(200,"user fetched successfully",userRepository.findById(id).orElse(null));
    }

    @Override
    public Response<UserModel> update(UserModel userModel) {
        return new Response<UserModel>(200,"user updated successfully", userRepository.save(userModel));
    }

    @Override
    public Response<UserModel> delete(long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            return new Response<>(200,"no user exists",null);
        }else{
            userRepository.deleteById(id);
            return new Response<>(200, "user deleted successfully",user.orElse(null));
        }
    }
}
