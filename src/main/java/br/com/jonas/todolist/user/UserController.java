package br.com.jonas.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body("users");
	}

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        User dbUser = this.userRepository.findByUserName(user.getUserName());
        
        if (dbUser != null) {
           return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Users exists");
        }
        var createdUser = this.userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
