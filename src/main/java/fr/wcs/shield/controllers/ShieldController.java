package fr.wcs.shield.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShieldController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the SHIELD";
    }

    @GetMapping("/avengers/assemble")
    public String avengersAssemble() {
        return  "Avengers... Assemble";
    }

    @GetMapping("/secret-bases")
    public List<String> secretBases() {
        List<String> schoolList = new ArrayList<>();
        schoolList.add("Biarritz");
        schoolList.add("Bordeaux");
        schoolList.add("La Loupe \uD83C\uDF32");
        schoolList.add("Lille");
        schoolList.add("Lyon");
        schoolList.add("Marseille");
        schoolList.add("Nantes");
        schoolList.add("Orléans");
        schoolList.add("Paris");
        schoolList.add("Reims");
        schoolList.add("Strasbourg");
        schoolList.add("Toulouse");
        schoolList.add("Tours");
        return schoolList;
    }

}
