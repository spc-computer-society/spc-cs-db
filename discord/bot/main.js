const Discord = require("discord.js");
const client = new Discord.Client();
client.login(process.env.token);
client.once('ready',() =>{
    console.log('Ready!');
});
client.on('message',message =>{
    if(message.content === 'Eric...'){
        return message.channel.send("Hello, this is Eric, from Indian Tech Support!");
    }
});