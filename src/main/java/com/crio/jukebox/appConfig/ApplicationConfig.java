package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.*;
import com.crio.jukebox.repositories.*;
import com.crio.jukebox.services.*;

public class ApplicationConfig {
    private final IAlbumRepository albumRepository = new AlbumRepository();
    private final ISongRepository songRepository = new SongRepository();
    private final IUserRepository userRepository = new UserRepository();

    private final IUserService userService = new UserService(userRepository,songRepository);
    private final ISongService songService = new SongService(songRepository,albumRepository);

    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songService);
    private final CreatePlayListCommand createPlayListCommand = new CreatePlayListCommand(userService);
    private final DeletePlayListCommand deletePlayListCommand = new DeletePlayListCommand(userService);
    private final ModifyPlayListCommand modifyPlayListCommand = new ModifyPlayListCommand(userService);
    private final PlayPlayListCommand playPlayListCommand = new PlayPlayListCommand(userService);
    private final PlaySongOnPlayListCommand playSongOnPlayListCommand = new PlaySongOnPlayListCommand(userService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("LOAD-DATA",loadDataCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlayListCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlayListCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST",playPlayListCommand);
        commandInvoker.register("PLAY-SONG",playSongOnPlayListCommand);
        return commandInvoker;
    }

}