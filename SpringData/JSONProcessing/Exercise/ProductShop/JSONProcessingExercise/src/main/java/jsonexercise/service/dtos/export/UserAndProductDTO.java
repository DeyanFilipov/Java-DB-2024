package jsonexercise.service.dtos.export;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UserAndProductDTO implements Serializable {

    @Expose
    private int usersCount;

    @Expose
    private List<UserSoldDTO> users;

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserSoldDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserSoldDTO> users) {
        this.users = users;
    }
}
