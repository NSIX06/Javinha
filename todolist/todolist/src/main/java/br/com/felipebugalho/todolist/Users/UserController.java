package br.com.felipebugalho.todolist.Users;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * todo Modificador (classificardores de classes) :
 * * public - qualquer um podde acessar a classe;
 * ! private - restrição maior, alguns atributos tem permissão;
 * ? protected - quando está na mesma estrutura no pacote que tem o acesso.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository userRepository;



    @PostMapping("/")
    public ResponseEntity create (@RequestBody UserModel userModel){
    
        var user = this.userRepository.findByUsername(userModel.getUsername());  

        if (user != null){
            //MENSAGEM DE ERRO
            //STATUS CODE 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }

        var passwordHashred = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());


        userModel.setPassword(passwordHashred);


        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);


    }

}
