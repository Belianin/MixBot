import java.util.Scanner;

public class JokeBot {

	public static void main(String[] args) {
		int jokeCounter = 0;
		Scanner input = new Scanner(System.in);
		String[] jokes = new String[] {"Kolobok hanged himself! :)", "Buratino is drown. :(",
				"Погуглил \"Как устроить лесной пожар?\". Получил 48500 зажигательных идей.",
				"Самая мощная организация в мире - это дебилы, у них везде свои люди."};
		
		System.out.println("Hello, my name is JokeBot.\nI am a chat-bot. I live to serve humans and tell hilarious jokes!\nWanna some?");
		loop1 : while (true)
		{			
			switch (input.nextLine().toLowerCase().replaceAll("\\s",""))
			{
			case "yes":
			{
				System.out.println(jokes[jokeCounter++ % jokes.length]);
				System.out.println("Another one?");
				break;
			}
			case "no":
			{
				System.out.println("Well, you can say \"stop\" to end our conversation\nOr may be you DO want a joke?");
				break;
			}
			case "stop":
			{
				System.out.println("Okay, goodbye!");
				break loop1;
			}
			default:
			{
				System.out.println("Sorry, i can't understand you.\nIf you wanna joke, just say \"yes\".\nOr if i bother you say \"stop\".");
				break;
			}
			}
		}

	}

}
