using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Threading.Tasks;
using Discord;
using Discord.WebSocket;
using Discord.Commands;

namespace Chicken_Xyfer
{
    public class Program
    {
        public static void Main(string[] args) => new Program().MainAsync().GetAwaiter().GetResult();

        string TOKEN = "Your Token"; //Get the token to store here
        private string PREFIX = "!"; //Prefix
        private DiscordSocketClient _client; //_client is the bot

        public async Task MainAsync()
        {
            _client = new DiscordSocketClient();

            _client.Log += Log; //Run the Log function

            await _client.LoginAsync(TokenType.Bot, TOKEN); //Start the bot
            await _client.StartAsync();

            _client.MessageReceived += MessageReceived; //Run the Main function

            await Task.Delay(-1); //Never end delay
        }

        private async Task MessageReceived(SocketMessage arg) //SocketMessage arg include message from user and system
        {
            var msg = arg as SocketUserMessage; //SocketUserMessage msg is the variable that is storing all information about the message received from user

            int argPos = 0;
            if (msg.HasStringPrefix(PREFIX, ref argPos)) //Check the prefix
            {
                /*
                Your
                Main
                Program
                Here
                */
            }
        }

        private Task Log(LogMessage arg) //This print out the log while starting the bot, and error info if any
        {
            Console.WriteLine(arg);
            return Task.CompletedTask;
        }
    }
}