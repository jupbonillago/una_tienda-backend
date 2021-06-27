package com.ingesoft2.controller;

import java.util.List;

import com.ingesoft2.mail.EmailBody;
import com.ingesoft2.mail.EmailService;
import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.RecoverPasswordToken;
import com.ingesoft2.models.Role;
import com.ingesoft2.pojo.MyProfilePOJO;
import com.ingesoft2.pojo.RegisterUserPOJO;
import com.ingesoft2.pojo.UpdateProfilePOJO;
import com.ingesoft2.services.PersonService;
import com.ingesoft2.services.RecoverPasswordTokenService;
import com.ingesoft2.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/person")
public class PersonController {

    private PersonService personService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private RecoverPasswordTokenService recoverPasswordTokenService;
    private EmailService emailService;

    @Autowired
    public PersonController( PersonService personService, RoleService roleService, PasswordEncoder passwordEncoder, RecoverPasswordTokenService recoverPasswordTokenService, EmailService emailService){
        this.personService = personService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.recoverPasswordTokenService = recoverPasswordTokenService;
        this.emailService = emailService;
    }

    @GetMapping("/list")
    public List<PersonDTO> getAllPersonDTOs() {
        return personService.findAllPeople();
    }

    @GetMapping("/profile")
    public MyProfilePOJO getPersonProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        MyProfilePOJO person = new MyProfilePOJO(); //Solo lo declaro para poder usar sus metodos
        
        return person.myProfilePOJO(personService.findByUsername(username));
    }
    
    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<Void> forgotPassword(@PathVariable("email") String email){
        PersonDTO person = personService.findByEmail(email);

        if(person != null){
            RecoverPasswordToken token = recoverPasswordTokenService.getJWToken(person);  
            String url = "http://localhost:8080/restore/" + token.getToken();
            try {
                String content = "<div style=\"background-color: #2c3e50; color: white;\"><img src=\"cid:logo\" alt=\"logo.png\" style=\"height: 100px;\"><div><h3>Solicitud de reestablecimiento de contraseña</h3><h4>¡Hola!</h4><p>Hemos recibido una solicitud para el cambio de contraseña a la cuenta enlazada a éste correo, si fuiste tu por favor sigue el proceso en el siguiente enlace que te brindaremos. Podrá usar este enlace durante la próxima hora, luego de esto vencerá y tendrá que solicitar uno nuevo. Si no realizo ninguna solicitud de cambio de contraseña, puede hacer caso omiso a este correo.</p><a class=\"button\" href=\""+ url +"\" style=\"color:white; box-shadow:inset 0px -3px 7px 0px #29bbff;background:linear-gradient(to bottom, #2dabf9 5%, #0688fa 100%);background-color:#2dabf9;border-radius:3px;border:1px solid #0b0e07;display:inline-block;cursor:pointer;color:#ffffff;font-family:Arial;font-size:15px;padding:9px 23px;text-decoration:none;text-shadow:0px 1px 0px #263666;\">Haga clic aquí para redirigirse al cambio de contraseña</a></div></div>";
                emailService.sendEmail(new EmailBody(email, content));
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/restore-password/{token}/{password}")
    public ResponseEntity<Void> forgotPassword(@PathVariable("token") String token, @PathVariable("password") String password){
        PersonDTO person = personService.findByUsername(recoverPasswordTokenService.getUsername(token));
        if(person != null){
            String passwordEncrypted = recoverPasswordTokenService.encryptPassword(password);
            person.setPassword(passwordEncrypted);
            personService.update(person);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addPerson(@RequestBody RegisterUserPOJO user) {

        Role role = roleService.findById(1);
        if (user != null) {
            PersonDTO user2 = new PersonDTO();
            user2.setName(user.getName());
            user2.setUsername(user.getUsername());
            user2.setEmail(user.getEmail());
            user2.setPassword(passwordEncoder.encode(user.getPassword()));
            user2.setPhoto(user.getPhoto());
            user2.setLocation(user.getLocation());
            user2.setPaypalId(user.getPaypalId());


            user2.setRoleId(role);
            personService.insert(user2);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) {


        if(id > 0) {
            if(personService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updatePerson(@RequestBody UpdateProfilePOJO person) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PersonDTO person2 = personService.findByUsername(username);
        person2.setName(person.getName());
        person2.setLocation(person.getLocation());
        personService.update(person2);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}