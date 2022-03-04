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
    private TextButton volver, jugar,ranking;
    private Label tiempo, resultado;
    private Image fondo;
    //Tiempo que ha durado la partida
    private long min;
    private long seg;


    public GameOverScreen(final MainGame game) {
        super(game);
        //damos valor por defecto a los minutos y a los segundos
        min=0;
        seg=0;
        //Instanciamos nuestro fondo
        fondo=new Image(game.getManager().get("podium.jpg", Texture.class));
        //definimos el stage
        stage=new Stage(new FitViewport(640,360));
        //instanciamos las skins
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //Instanciamos los botones y las labels que colocaremos en nuestra pantalla
        volver = new TextButton("Volver al menu", skin);
        jugar = new TextButton("Volver a jugar", skin);
        ranking = new TextButton("Ranking", skin);
        tiempo=new Label("Tu tiempo: ",skin);
        resultado=new Label(seg+"Seg",skin);

        //Definimos un boton para volver a la pantalla de menu
        volver.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menuScreen);
            }
        });
        //Definimos un boton para volver a jugar que nos llevara a la pantalla
        //de seleccion de jugador
        jugar.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.seleccionScreen);
            }
        });
        //Definimos un boton para visualizar el ranking
        ranking.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.rankingScreen);
            }
        });

        //Asignamos tamaño y posicion a nuestros elementos que cargaremos en la pantalla
        fondo.setSize(630,450);
        tiempo.setPosition(280,230);
        jugar.setSize(150, 50);
        jugar.setPosition(45, 80);
        volver.setSize(150, 50);
        volver.setPosition(430, 80);
        ranking.setSize(150, 50);
        ranking.setPosition(245, 80);
        resultado.setPosition(300,180);

        //Añadimos todos estos recursos como actores del stage
        stage.addActor(fondo);
        stage.addActor(tiempo);
        stage.addActor(volver);
        stage.addActor(jugar);
        stage.addActor(resultado);
        stage.addActor(ranking);
    }
    @Override
    public void show() {
        //Se ejecuta solo cuando se inicia la pantalla
        //Procesa todos los procesos del stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        //Cuando cerramos la pantalla ponemos a null este InputProcessor
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        //eliminamos el stage y las skin
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        //pintamos el fondo de negro
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Actalizamos y pintamos el stage
        stage.act();
        stage.draw();
    }

    //Metodo para obtener el tiempo que ha transcurrido de partida
    public String escribirTiempo(long time){
        String fin="";
        seg=time;
        //si los segundos superan los 60, sumamos un minuto y le
        //restamos el valor correspondiente a la variable de segundos
        if(seg>60){
            min=min+1;
            seg=time-(min*60);
            fin=min+" Min "+seg+" Seg";

        }else{
            //si no hemos superado el minuto solo escribiremos los segundos
            fin=seg+" Seg";
        }
        //cambiamos la label del resultado con los datos obtenidos
        resultado.setText(fin);
        //Retornamos la label para poder disponer del String que guarda el tiempo
        return fin;
    }
}
