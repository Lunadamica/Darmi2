package com.darmi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen extends BaseScreen{
    private Stage stage;
    private Skin skin;
    private TextButton volver, jugar;
    private Label tiempo, resultado;
    private Image fondo;
    //Tiempo que ha durado la partida
    private long min;
    private long seg;


    public GameOverScreen(final MainGame game) {
        super(game);
        min=0;
        seg=0;
        fondo=new Image(game.getManager().get("podium.jpg", Texture.class));
        stage=new Stage(new FitViewport(640,360));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        volver = new TextButton("Volver al menu", skin);
        jugar = new TextButton("Volver a jugar", skin);
        tiempo=new Label("Tu tiempo es: ",skin);
        resultado=new Label(seg+"S",skin);


        volver.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menuScreen);
            }
        });

        jugar.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.seleccionScreen);
            }
        });
        fondo.setSize(630,450);
        tiempo.setPosition(255,230);
        jugar.setSize(150, 50);
        jugar.setPosition(45, 80);
        volver.setSize(150, 50);
        volver.setPosition(420, 80);
        resultado.setPosition(295,180);


        stage.addActor(fondo);
        stage.addActor(tiempo);
        stage.addActor(volver);
        stage.addActor(jugar);
        stage.addActor(resultado);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    //Metodo para obtener el tiempo que ha transcurrido de partida
    public String escribirTiempo(long time){
        String fin="";
        seg=time;
        if(seg>60){
            min=min+1;
            seg=time-(min*60);
            fin=min+" Min "+seg+" Seg";

        }else{
            fin=seg+" Seg";
        }
        resultado.setText(fin);
        return fin;
    }
}
