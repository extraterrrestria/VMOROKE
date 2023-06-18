package ru.hse.vmoroke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        return (text).toLowerCase().split(substr + "*{0,3}\\s").length - 1;
    }
    //  Эта функция выводит количество вхождений строки в подстроку (для того, чтобы легко считать количество слов каждой категории в тексте)

    /**
     * Считает количество слов, связанных с радостью, в тексте.
     *
     * @param txt текст, в котором выполняется подсчет
     * @return количество слов, связанных с радостью
     */
    public static int counthappy(String txt){
        String file = "wordshappy.txt";
        int res = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                res += count(line, txt);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
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
        String file = "wordssad.txt";
        int res = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                res += count(line, txt);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
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
        String file = "wordssurprise.txt";
        int res = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                res += count(line, txt);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
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
        String file = "wordsangry.txt";
        int res = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                res += count(line, txt);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
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
        String file = "wordsdisgust.txt";
        int res = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                res += count(line, txt);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
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
        String file = "wordsfear.txt";
        int res = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                res += count(line, txt);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
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

    public static void addCriteria(String word, String emotionType){ // записывает в нужный файл новую строку
        //эта строка будет использоваться в качестве одного из критериев этой эмоции
        //String[] words = new String[500];
        List<String> words = new ArrayList<>();
        String file = "wordshappy.txt";
        System.out.println(file.equals("words" + emotionType + ".txt"));

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                words.add(line);
                i++;
            }
            words.add(word);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for(int i = 0; i < words.size(); i++){
                bw.write(words.get(i));
                bw.newLine();

            }

            System.out.println("Запись в файл завершена.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

}