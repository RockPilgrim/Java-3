/**
 * Java-3 Homework-1
 *
 * Realized phonebook
 * add methods witch: Swaps two elements of array, and Convert array to HashSet
 *
 * @author Tokarev Timofei
 * @version dated 07.11.2018
 */

import java.util.HashMap;
import java.util.HashSet;


class PhoneBox {
    public static final String SAVE = "Save";
    HashMap<String, HashSet<String>> hm;

    public PhoneBox() {
        this.hm = new HashMap<>();
    }

    public void add(String name, String phone) {
        HashSet<String> hs = hm.getOrDefault(name, new HashSet<>());
        hs.add(phone);
        hm.put(name, hs);
        System.out.println("Новая зпись o "+ name +" добавлена" );
    }
    public void add(String name, HashSet<String> phones){
        hm.put(name, phones);
    }
    private void add(String name,String[] phones){    //Convert array to HashSet
        HashSet<String> hs = hm.getOrDefault(name, new HashSet<>());
        for (String o:phones) {
            hs.add(o);
        }
        hm.put(name,hs);
    }
    public void findString(String name) {
        if(hm.containsKey(name)) {
            System.out.println(name+hm.get(name));
        } else {
            System.out.println("такой фамилии нет");
        }
    }
    public static void main(String[] args) {
        PhoneBox book = new PhoneBox();
        book.add("Ivanov", "123");
        book.add("Ivanov", "124");
        book.add("Ivanov", "125");
        book.add("Petrov", "445");
        book.add("Petrov", "444");
        book.add("Petrov", "446");

        String[] phones = {"226", "227", "228"};
        book.add("Ivanov",phones);

        book.findString("Ivanov");
        book.findString("Petrov");
        book.findString("Petrasdovjhk");
        book.change("Ivanov","Petrov");
    }

    private void change(String name1, String name2){    // Swaps two elements of array
        add(SAVE,hm.get(name1));
        hm.replace(name1,hm.get(name2));
        hm.replace(name2, hm.get(SAVE));
        hm.remove(SAVE);
        System.out.println(name2+hm.get(name2));    //Check
        System.out.println(name1+hm.get(name1));
    }
}