package sirine.souissi.myown;


public class Profil {
    public String getName;
    String name , lastname , number;

    public Profil(String name, String lastname, String number) {
        this.name = name;
        this.lastname = lastname;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public int getName() {
        return 0;
    }

    public int getLastname() {
        return 0;
    }

    public int getNumber() {
        return 0;
    }

    public void setName(String newName) {
    }

    public void setLastname(String newLastname) {
    }

    public void setNumber(String newNumber) {
    }


}
