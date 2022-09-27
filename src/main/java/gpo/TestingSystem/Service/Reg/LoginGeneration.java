package gpo.TestingSystem.Service.Reg;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LoginGeneration {

    private static String login;



    public static String loginGeneration(String name, String surname)
    {
        String first="";
        String second="";

        for(int i =0; i< 4;i++)
        {
            if(i!=3) {
                first += surname.toCharArray()[i];
                second += name.toCharArray()[i];
            }

        }
        login = first+second;

        for(int i=0;i<5;i++)
        {
            login += (int) (Math.random()*9);
        }

        return login.toUpperCase();
    }

}
