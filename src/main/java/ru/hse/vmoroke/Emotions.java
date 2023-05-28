public class Emotions {
    int emotionalLevel = 0;
    int happy = 0;
    int sad = 0;
    int surprise = 0;
    int angry = 0;
    int disgust = 0;
    int fear = 0;
    int apathy = 1;

    public static int count(String substr, String text){
        return (text + "\0").split(substr).length - 1;
    }
    //  Эта функция выводит количество вхождений строки в подстроку (для того, чтобы легко считать количество слов каждой категории в тексте)

    public static int counthappy(String txt){
        String[] happyWords = {"рад","рада","рады","радо","радостный","радостная","радостное","радостный","счастлив", "счастлива","счастливый","счастливая", "счастливое","счастливые","счастье","хороший","хорошая","хорошие","хорошее","приятно", "хорошо"};
        int res = 0;
        for(int i = 0; i < happyWords.length; i++){
            res += count(happyWords[i], txt);
        }
        return res;
    }
    public static int countsad(String txt){
        String[] sadWords = {"грустный","грустная","грустное","грустные","печальный","печальная","печальное","печальные","плохо","очень плохо","плохо","плохой","плохая","плохое","плохие","тоска","тоскливо","тоски","тоску","тосковать","грущу","тоскую","расстраиваюсь","расстроен","расстроена","расстроены"};
        int res = 0;
        for(int i = 0; i < sadWords.length; i++){
            res += count(sadWords[i], txt);
        }
        return res;
    }
    public static int countsurprise(String txt){
        String[] surpriseWords = {"удивлен","удивлена","удивительно","удивлены","удивлённо","сюрприз","неожиданно","неожиданный","нежданно","негаданно","чудесно","чудо","удивление","изумительно","неожиданная","неожиданное","неожиданные","нежданный","нежданная","потрясен","потрясена","потрясены","приятно удивлен","приятно удивлена","удивлен","удивлена","удивления"};
        int res = 0;
        for(int i = 0; i < surpriseWords.length; i++){
            res += count(surpriseWords[i], txt);
        }
        return res;
    }
    public static int countangry(String txt){
        String[] angryWords = {"зол","зла","злы","злюсь","злится","злятся","зол","зла","зло","ударить","удар","удары","ударом","бить","злюсь","злимся","злобный","злобная","злобное","зловредный","злоба","раздражен","раздражена","токсичный","раздражение","злость"};
        int res = 0;
        for(int i = 0; i < angryWords.length; i++){
            res += count(angryWords[i], txt);
        }
        return res;
    }
    public static int countdisgust(String txt){
        String[] disgustWords = {"фу","гадость","противно","гадко","неприятно","неприятный","вонючий","вонючая","вонючее","вонючие","неприятный","нериятная","неприятное","неприятные","противные","фу гадость","гадко","противный","противная","противное","противные","противному","противной","неприятно","неприятно"};
        int res = 0;
        for(int i = 0; i < disgustWords.length; i++){
            res += count(disgustWords[i], txt);
        }
        return res;
    }
    public static int countfear(String txt){
        String[] fearWords = {"страх","страх","боюсь","боязнь","страшно","страшный","страшная","страшное","страшные","страшных","страхе","пугает","пугающий", "пугающая","пугающее","пугающие","испуг","испугался","испугаться","испугана","испуган","боязно","боязнь","фобия","кошмар"};
        int res = 0;
        for(int i = 0; i < fearWords.length; i++){
            res += count(fearWords[i], txt);
        }
        return res;
    }
    public int countapathy(String txt){
        int res = 1;
        int spacenumber = count(" ", txt);
        if(spacenumber - this.emotionalLevel > res){
            res = spacenumber - this.emotionalLevel;
        }
        return res;
    }

    private void setHappy(String text){this.happy = counthappy(text);}
    private void setSad(String text){this.sad = countsad(text);}
    private void setSurprise(String text){this.surprise = countsurprise(text);}
    private void setAngry(String text){this.angry = countangry(text);}
    private void setDisgust(String text){this.disgust = countdisgust(text);}
    private void setFear(String text){this.fear = countfear(text);}
    private void setEmotionalLevel(){
        this.emotionalLevel = this.happy + this.sad + this.surprise + this.angry + this.disgust + this.fear;
    }
    private void setApathy(String text){
        this.apathy = countapathy(text);
    }
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