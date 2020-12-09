////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;

public class User {
    final int id;
    final String name;
    final String surname;
    final int age;

    public User(int _id, String _name, String _surname, int _age) {
        if(_id<0) {
            _id=0;
        }
        id=_id;
        name=_name;
        surname=_surname;
        if(_age<0) {
            _age=0;
        }
        age=_age;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public int getAge() {return age;}
}
