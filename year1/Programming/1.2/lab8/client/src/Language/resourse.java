package Language;

import java.util.ListResourceBundle;

public class resourse extends ListResourceBundle {

    public static final Object[][] content = {
            {"StartPort", "Порт"},
            {"StartEnter","Далее"},
            {"StartEnterPort","Введите порт"},
            {"sampleUser","Логин"},
            {"samplePassword","Пароль"},
            {"sampleLogin","Войти"},
            {"wrongData","Введены некорректные данные!"},
            {"sampleRegistration","Зарегистрироваться"},
            {"RegisterUser","Логин"},
            {"RegisterPassword","Пароль"},
            {"RegisterRegister","Зарегистрироваться"},

            {"help","Помощь"},
            {"info", "Инфа"},
            {"history", "История"},
            {"middleprice", "Срений доход"},
            {"price", "Доход"},
            {"fullName", "Все названия"},
            {"Org", "Мои организации"},
            {"Allorg","Все организации"},
            {"animation", "Анимация"},
            {"result", "Результат"},
            {"logout", "Выйти"},

            {"Name","Название(int)"},
            {"Coordinates","Местоположение"},
            {"Annual Turnover","Доход"},
            {"Full name","Полное название"},
            {"Employee Count","Работники"},
            {"Type","Тип"},
            {"Postal address","Адрес"},
            {"Id","Id"},
            {"Delete","Удалить"},
            {"Edit","Изменить"},
            {"Date", "Дата"}
    };

    @Override
    protected Object[][] getContents() {
        return content;
    }
}
