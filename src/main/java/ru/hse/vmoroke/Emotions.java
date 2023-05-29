package ru.hse.vmoroke;

/**
 * Класс, представляющий эмоции и их подсчет в тексте.
 */
public class Emotions {
    int emotionalLevel = 0;
    int happy = 0;
    int sad = 0;
    int surprise = 0;
    int angry = 0;
    int disgust = 0;
    int fear = 0;
    int apathy = 1;

    /**
     * Считает количество вхождений подстроки в строку.
     *
     * @param substr подстрока, которую нужно найти
     * @param text   строка, в которой выполняется поиск
     * @return количество вхождений подстроки в строку
     */
    public static int count(String substr, String text){
        return (text + "\0").split(substr).length - 1;
    }
    //  Эта функция выводит количество вхождений строки в подстроку (для того, чтобы легко считать количество слов каждой категории в тексте)

    /**
     * Считает количество слов, связанных с радостью, в тексте.
     *
     * @param txt текст, в котором выполняется подсчет
     * @return количество слов, связанных с радостью
     */
    public static int counthappy(String txt){
        String[] happyWords = {"рад","рада","рады","радо","радостный","радостная","радостное","радостный","счастлив", "счастлива","счастливый","счастливая", "счастливое","счастливые","счастье","хороший","хорошая","хорошие","хорошее","приятно", "хорошо"};
        int res = 0;
        for(int i = 0; i < happyWords.length; i++){
            res += count(happyWords[i], txt);
        }
        return res;
    }
    /**
     * Возвращает количество слов, связанных с грустью, в тексте.
     *
     * @param txt текст, в котором производится поиск
     * @return количество слов, связанных с грустью
     */
    public static int countsad(String txt){
        String[] sadWords = {"грустный","грустная","грустное","грустные","печальный","печальная","печальное","печальные","плохо","очень плохо","плохо","плохой","плохая","плохое","плохие","тоска","тоскливо","тоски","тоску","тосковать","грущу","тоскую","расстраиваюсь","расстроен","расстроена","расстроены"};
        int res = 0;
        for(int i = 0; i < sadWords.length; i++){
            res += count(sadWords[i], txt);
        }
        return res;
    }
    /**
     * Возвращает количество слов, связанных с удивлением, в тексте.
     *
     * @param txt текст, в котором производится поиск
     * @return количество слов, связанных с удивлением
     */
    public static int countsurprise(String txt){
        String[] surpriseWords = {"удивлен","удивлена","удивительно","удивлены","удивлённо","сюрприз","неожиданно","неожиданный","нежданно","негаданно","чудесно","чудо","удивление","изумительно","неожиданная","неожиданное","неожиданные","нежданный","нежданная","потрясен","потрясена","потрясены","приятно удивлен","приятно удивлена","удивлен","удивлена","удивления"};
        int res = 0;
        for(int i = 0; i < surpriseWords.length; i++){
            res += count(surpriseWords[i], txt);
        }
        return res;
    }
    /**
     * Возвращает количество слов, связанных с гневом, в тексте.
     *
     * @param txt текст, в котором производится поиск
     * @return количество слов, связанных с гневом
     */
    public static int countangry(String txt){
        String[] angryWords = {"зол","зла","злы","злюсь","злится","злятся","зол","зла","зло","ударить","удар","удары","ударом","бить","злюсь","злимся","злобный","злобная","злобное","зловредный","злоба","раздражен","раздражена","токсичный","раздражение","злость"};
        int res = 0;
        for(int i = 0; i < angryWords.length; i++){
            res += count(angryWords[i], txt);
        }
        return res;
    }

    /**
     * Возвращает количество слов, связанных с отвращением, в тексте.
     *
     * @param txt текст, в котором производится поиск
     * @return количество слов, связанных с отвращением
     */
    public static int countdisgust(String txt){
        String[] disgustWords = {"фу","гадость","противно","гадко","неприятно","неприятный","вонючий","вонючая","вонючее","вонючие","неприятный","нериятная","неприятное","неприятные","противные","фу гадость","гадко","противный","противная","противное","противные","противному","противной","неприятно","неприятно"};
        int res = 0;
        for(int i = 0; i < disgustWords.length; i++){
            res += count(disgustWords[i], txt);
        }
        return res;
    }

    /**
     * Возвращает количество слов, связанных со страхом, в тексте.
     *
     * @param txt текст, в котором производится поиск
     * @return количество слов, связанных со страхом
     */
    public static int countfear(String txt){
        String[] fearWords = {"страх","страх","боюсь","боязнь","страшно","страшный","страшная","страшное","страшные","страшных","страхе","пугает","пугающий", "пугающая","пугающее","пугающие","испуг","испугался","испугаться","испугана","испуган","боязно","боязнь","фобия","кошмар"};
        int res = 0;
        for(int i = 0; i < fearWords.length; i++){
            res += count(fearWords[i], txt);
        }
        return res;
    }
    /**
     * Возвращает количество слов, связанных с безразличием, в тексте.
     *
     * @param txt текст, в котором производится поиск
     * @return количество слов, связанных с безразличием
     */
    public int countapathy(String txt){
        int res = 1;
        int spacenumber = count(" ", txt);
        if(spacenumber - this.emotionalLevel > res){
            res = spacenumber - this.emotionalLevel;
        }
        return res;
    }

    /**
     * Устанавливает значение переменной happy на основе текста.
     *
     * @param text текст, на основе которого устанавливается значение
     */
    private void setHappy(String text){this.happy = counthappy(text);}

    /**
     * Устанавливает значение переменной sad на основе текста.
     *
     * @param text текст, на основе которого устанавливается значение
     */
    private void setSad(String text){this.sad = countsad(text);}

    /**
     * Устанавливает значение переменной surprise на основе текста.
     *
     * @param text текст, на основе которого устанавливается значение
     */
    private void setSurprise(String text){this.surprise = countsurprise(text);}

    /**
     * Устанавливает значение переменной angry на основе текста.
     *
     * @param text текст, на основе которого устанавливается значение
     */
    private void setAngry(String text){this.angry = countangry(text);}

    /**
     * Устанавливает значение переменной disgust на основе текста.
     *
     * @param text текст, на основе которого устанавливается значение
     */
    private void setDisgust(String text){this.disgust = countdisgust(text);}

    /**
     * Устанавливает значение переменной fear на основе текста.
     *
     * @param text текст, на основе которого устанавливается значение
     */
    private void setFear(String text){this.fear = countfear(text);}
    /**
     * Устанавливает значение переменной emotionalLevel, основываясь на значениях остальных эмоций.
     */
    private void setEmotionalLevel(){
        this.emotionalLevel = this.happy + this.sad + this.surprise + this.angry + this.disgust + this.fear;
    }
    /**
     * Устанавливает значение переменной apathy на основе текста.
     *
     * @param text текст, на основе которого устанавливается значение
     * @return значение переменной apathy
     */
    private void setApathy(String text){
        this.apathy = countapathy(text);
    }

    /**
     * Устанавливает значения всех переменных на основе текста.
     *
     * @param text текст, на основе которого устанавливаются значения
     */
    public void setAll(String text){
        setHappy(text);
        setSad(text);
        setSurprise(text);
        setAngry(text);
        setDisgust(text);
        setFear(text);
        setEmotionalLevel();
        setApathy(text);
    }

}