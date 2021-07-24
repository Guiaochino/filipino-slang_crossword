package com.example.filipino_slang;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends AppCompatActivity {

    // FOR ANDROID
    private TextView txtGameMode;
    private Button btnBack;
    private GridView puzzle_board;
    private TextView description_display;

    // get keyboard layouts for key access
    private Button q_key, w_key, e_key, r_key, t_key, y_key, u_key, i_key, o_key, p_key, a_key, s_key,
            d_key, f_key, g_key, h_key, j_key, k_key, l_key, z_key, x_key, c_key, v_key, b_key, n_key,
            m_key, del_key;

    // data used in the application
    private static final String[] easyLetterWord = {"LODI", "AWIT", "JOWA", "ONIS", "NYEK", "KERI",
            "ARAT", "KYAH", "DAKS", "MUMU", "MARS", "NOTA", "KEBS", "SSOB", "AGIK", "DEDO", "WAFU",
            "WAFA", "ANDA"};

    private static final String[] easyDescription = {"taong iniidolo", "pinagsamang aw masakit",
            "tawag sa babae o lalaking iniirog", "sino?", "isang reaksyon kapag hindi nakakatuwa ang pagbibiro",
            "kayang-kaya gawin ang isang bagay", "tara", "nakatatandang kapatid o poging lalaki",
            "malaki", "multo", "babaeng kaibigan, kadalasang ginagamit ng mga matatanda",
            "titi or pututoy", "walang pake, bahala na", "taong nirerespeto or mataas anf posisyon",
            "ekspresyon kapag nagulat", "patay na", "gwapo o pogi", "maganda", "pera o kwarta"};

    private static final String[] normalLetterWord = {"CHIKA", "ERMAT", "ERPAT", "ECHOS", "ATABS",
            "ALBOR", "AKESH", "EDNIS", "WERPA", "HAVEY", "WALEY", "ASTIG", "GIGIL", "OMSIM"};

    private static final String[] normalDescription = {"kwento o balita", "nanay o ina", "tatay o ama",
            "biro lang", "bata", "paghingi ng isang bagay", "ako", "pagsigarilyo o pasinde",
            "power o kaya yan", "nakakatawa", "hindi nakakatawa", "reaksyon kapag may hinahangaan",
            "galit na galit", "pagsang-ayon sa sinabi ng iba"};

    private static final String[] hardLetterWord = {"WALWAL", "JONTIS", "KENKOY", "CHIBOG", "DEKWAT",
            "SHUNGA", "PABEBE", "CHURVA", "TSEKOT"};

    private static final String[] hardDescription = {"lakwatsa o gala o pagwawala", "buntis",
            "joker o nakakatawang tao", "kain", "pagkuha ng gamit na hindi sa iyo o pagnanakaw",
            "walang pinag-aralan o hindi naintidihan", "babaeng pakyut lang alam", "sabi sabi", "kotse"};

    // variables to be used
    public static int row ,col;
    public char[][] board;
    public ArrayList<String> converted_board;
    public ArrayList<String> toUseWords;
    public ArrayList<String> toUseDescription;
    public ArrayList<String> inUse;
    public Random randomizer = new Random();

    // Create instances of categories
    private Words easyCategory = new Words(easyLetterWord, easyDescription);
    private Words normalCategory = new Words(normalLetterWord, normalDescription);
    private Words hardCategory = new Words(hardLetterWord, hardDescription);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // Getting the method in the Main Activity
        Intent playGame = getIntent();
        String gameMode = playGame.getStringExtra(MainActivity.whatMode);

        // declaration for puzzle and description
        description_display = (TextView) findViewById(R.id.description_container);
        puzzle_board = (GridView) findViewById(R.id.puzzle_container);

        // declaration for keyboardlayout
        q_key = (Button) findViewById(R.id.q_key);
        w_key = (Button) findViewById(R.id.w_key);
        e_key = (Button) findViewById(R.id.e_key);
        r_key = (Button) findViewById(R.id.r_key);
        t_key = (Button) findViewById(R.id.t_key);
        y_key = (Button) findViewById(R.id.y_key);
        u_key = (Button) findViewById(R.id.u_key);
        i_key = (Button) findViewById(R.id.i_key);
        o_key = (Button) findViewById(R.id.o_key);
        p_key = (Button) findViewById(R.id.p_key);
        a_key = (Button) findViewById(R.id.a_key);
        s_key = (Button) findViewById(R.id.s_key);
        d_key = (Button) findViewById(R.id.d_key);
        f_key = (Button) findViewById(R.id.f_key);
        g_key = (Button) findViewById(R.id.g_key);
        h_key = (Button) findViewById(R.id.h_key);
        j_key = (Button) findViewById(R.id.j_key);
        k_key = (Button) findViewById(R.id.k_key);
        l_key = (Button) findViewById(R.id.l_key);
        z_key = (Button) findViewById(R.id.z_key);
        x_key = (Button) findViewById(R.id.x_key);
        c_key = (Button) findViewById(R.id.c_key);
        v_key = (Button) findViewById(R.id.v_key);
        b_key = (Button) findViewById(R.id.b_key);
        n_key = (Button) findViewById(R.id.n_key);
        m_key = (Button) findViewById(R.id.m_key);
        del_key = (Button) findViewById(R.id.del_key);

        // Declaration of Game Mode Text View
        txtGameMode = findViewById(R.id.txtGameMode);
        txtGameMode.setText(gameMode);

        // Declaration of Return to Menu Screen Button
        btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuScreen();
            }
        });

        // Somewhere here is the problem --------
        row = gameGrid(gameMode.toLowerCase())[0];
        col = gameGrid(gameMode.toLowerCase())[1];
        toUseWords = new ArrayList<>();
        toUseWords = gameDifficulty(gameMode.toLowerCase(), row);
//        toUseDescription = new ArrayList<String>();
//        inUse = new ArrayList<String>();

        // get game mode easy = 4, normal = 5, hard = 6 letters
//        if (gameMode.toLowerCase() == "easy mode") {
//            row = 8;
//            col = 4;
//            toUseWords = getToUseWords(easyCategory.getWord(), toUseWords, inUse, row);
//            toUseDescription = getWordDefinitions(easyCategory.getWord(), toUseWords, easyCategory.getDesription());
//        }
//        else if (gameMode.toLowerCase() == "normal mode"){
//            row = 6;
//            col = 5;
//            toUseWords = getToUseWords(normalCategory.getWord(), toUseWords, inUse, row);
//            toUseDescription = getWordDefinitions(normalCategory.getWord(), toUseWords, normalCategory.getDesription());
//        }
//        else if (gameMode.toLowerCase() == "hard mode"){
//            row = 6;
//            col = 6;
//            toUseWords = getToUseWords(hardCategory.getWord(), toUseWords, inUse, row);
//            toUseDescription = getWordDefinitions(hardCategory.getWord(), toUseWords, hardCategory.getDesription());
//        }

        // ---------- Problem Up to here

        //convert 2D array to 1D array to be passed in in the adapter
        // converted_board = board_convert(board);

        // for debugging purposes
        description_display.setText(String.valueOf(toUseWords.size()));

        // set what to display in gridview
//        Mainadapter letterAdapter = new Mainadapter(GameScreen.this, toUseWords);
//        puzzle_board.setAdapter(letterAdapter);
//
//        puzzle_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // handle the item clicked
//                Toast.makeText(getApplicationContext(), "You Clicked " + converted_board.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void openMenuScreen(){
        Intent returnMenuScreen = new Intent(this, ExitPrompt.class);
        startActivity(returnMenuScreen);
    }

    public int[] gameGrid(String difficulty){

        int[] grid = new int[2];
        // Categories of difficulty
        String easy = "easy mode";
        String normal  = "normal mode";
        String hard = "hard mode";

        if (difficulty == easy){
            grid[0] = 8;
            grid[1] = 4;

            return grid;

        } else if (difficulty == normal){
            grid[0] = 5;
            grid[1] = 6;

            return grid;

        } else if(difficulty == hard){
            grid[0] = 6;
            grid[1] = 6;

            return grid;
        }
        return grid;
    }

    public ArrayList<String> gameDifficulty(String difficulty, int minWord) {

        // Categories of difficulty
        String easy = "easy mode";
        String normal  = "normal mode";
        String hard = "hard mode";

        ArrayList<String> picked_words = new ArrayList<>();

        if (difficulty == easy){
            picked_words = getToUseWords(easyCategory.getWord(), minWord);
        } else if (difficulty == normal){
            picked_words = getToUseWords(normalCategory.getWord(), minWord);
        } else if (difficulty == hard){
            picked_words = getToUseWords(hardCategory.getWord(), minWord);
        }
        return picked_words;
    }

    public ArrayList<String> getToUseWords (String[] toGet, int minWord){

        ArrayList<String> wordToUse = new ArrayList<>();

        while(wordToUse.size() < minWord) {
            int picker = randomizer.nextInt(toGet.length);
            int[] picker_values = new int[]{};

            if (picker_values.length < minWord || picker_values == null) {
                for (int i = 0; i < minWord; i++) {
                    for (int j = 0; j < picker_values.length; j++) {
                        if (picker == picker_values[j]) {
                            continue;
                        } else {
                            picker_values[i] = picker;
                        }
                    }
                }
            }
        }
        return wordToUse;
    }

    public ArrayList<String> getWordDefinitions (String[] toGet, ArrayList<String> currentlyUsedWord, String[] desription){
        ArrayList<String> currentDefinition = new ArrayList<>();
        for (String word : currentlyUsedWord){
            for (int i = 0; i < toGet.length; i++){
                if (word == toGet[i]){
                    currentDefinition.add(desription[i]);
                } else continue;
            }
        }
        return currentDefinition;
    }

    private ArrayList<String> board_convert(char[][] grid){

        ArrayList<String> converted_2d = new ArrayList<>();

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                converted_2d.add(String.valueOf(grid[i][j]));
            }
        }

        return converted_2d;
    }
}