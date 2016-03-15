package br.ufal.ic.system;

import java.awt.Menu;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Locale.Category;

import javax.sql.rowset.spi.TransactionalWriter;

import br.ufal.ic.exceptions.CommunityNotFoundException;
import br.ufal.ic.exceptions.IncompleteDataException;
import br.ufal.ic.exceptions.ResearcherExistException;
import br.ufal.ic.exceptions.UserNotFoundException;
import br.ufal.ic.systemComponentes.CommunitiesDataBase;
import br.ufal.ic.systemComponentes.Community;
import br.ufal.ic.systemComponentes.Message;
import br.ufal.ic.systemComponentes.Researcher;
import br.ufal.ic.systemComponentes.ResearchersDataBase;

public class IResearcherSystem {

	protected ResearchersDataBase researchersDataBase;
	protected CommunitiesDataBase communitiesDataBase;
	protected Researcher CurrentResearcher;
	
	public IResearcherSystem() {
		this.researchersDataBase = new ResearchersDataBase();
		this.communitiesDataBase = new CommunitiesDataBase();
		this.CurrentResearcher = new Researcher();
	}
	
	public static void main(String[] args) {
		IResearcherSystem iResearcherSystem = new IResearcherSystem();
		boolean end = false;
		int input;
		Scanner scanner = null;
		
		
		while(!end){
			try{
				System.out.println("\n1-Login\n" + "2-Register\n" + "3-Exit" + "\n");
				scanner = new Scanner(System.in);
				input = scanner.nextInt();
				
				if(input == 1){
					iResearcherSystem.login();
					
				}else if(input == 2){
					iResearcherSystem.registerResearcher();
				}else if(input == 3){
					iResearcherSystem.printResearchers();
				}else{
					throw new InputMismatchException();
				}
				
			}catch(InputMismatchException i){
				System.out.println("\n*Digite uma opção válida*\n");
			}
			
			
			
			
		}
		scanner.close();
		

	}
	
	public ResearchersDataBase getResearchersDataBase() {
		return researchersDataBase;
	}
	public void setResearchersDataBase(ResearchersDataBase researchersDataBase) {
		this.researchersDataBase = researchersDataBase;
	}
	public CommunitiesDataBase getCommunitiesDataBase() {
		return communitiesDataBase;
	}
	public void setCommunitiesDataBase(CommunitiesDataBase communitiesDataBase) {
		this.communitiesDataBase = communitiesDataBase;
	}
	public Researcher getCurrentResearcher() {
		return CurrentResearcher;
	}
	
	public void printResearchers(){
		for(int i = 0; i < this.researchersDataBase.findAll().size(); i++){
			
			System.out.println(this.researchersDataBase.findAll().get(i).toString());
		}
	}
	public boolean researcherExist(String login){
		for(int i = 0; i < this.researchersDataBase.findAll().size(); i++){
			if(this.researchersDataBase.findAll().get(i).getLogin().equals(login)){
				return true;
			}
		}
		
		return false;
	}
	
	public void setCurrentResearcher(Researcher currentResearcher) {
		CurrentResearcher = currentResearcher;
	}
	
	public void registerResearcher(){
		Scanner scanner;
		String name, password, login;
		Researcher researcher;
		
		try{
			System.out.println("Digite seu nome");
			scanner = new Scanner(System.in);
			name = scanner.nextLine();
			
			if(name.length() == 0){
				throw new IncompleteDataException("\n*Registro cancelado\nvocê não digitou um nome*\n");
			}
			
			System.out.println("Digite seu login");
			scanner = new Scanner(System.in);
			login = scanner.nextLine();
		
			if(login.length() == 0){
				throw new IncompleteDataException("\n*Registro cancelado\nvocê não digitou um login*\n");
			
			}else if(this.researcherExist(login)){
				throw new ResearcherExistException("\n*Usuário já existe*\n");
				
			}
			
			System.out.println("Digite sua senha");
			scanner = new Scanner(System.in);
			password = scanner.nextLine();
			
			if(password.length() == 0){
				throw new IncompleteDataException("\n*Registro cancelado\nvocê não digitou uma senha*\n");
			}
			
			researcher = new Researcher();
			researcher.setName(name);
			researcher.setLogin(login);
			researcher.setPassword(password);
			
			this.researchersDataBase.add(researcher);
			
			
			
		}catch(IncompleteDataException | ResearcherExistException i){
			System.out.println(i.getMessage());
		}
		
		
		
	}
	public void login(){
		
		try{
			Scanner scanner = null;
			String login, password;
			
			
			System.out.println("\nDigite seu login\n");
			scanner = new Scanner(System.in);
			login = scanner.nextLine();
			
			if(login.length() == 0){
				throw new IncompleteDataException("\n*Você não digitou nada*\n");
			}
			
			System.out.println("\nDigite sua senha\n");
			scanner = new Scanner(System.in);
			password = scanner.nextLine();
			
			if(password.length() == 0){
				throw new IncompleteDataException("\n*Você não digitou nada*\n");
			}
			
			if(this.researcherExist(login)){
				
				if(this.researchersDataBase.search(login).getPassword().equals(password)){
					System.out.println("\n*Você fez login com sucesso*\n");
					this.CurrentResearcher = this.researchersDataBase.search(login);
					this.dashboard();
				}else{
					throw new UserNotFoundException("\n*Usuário não existe*\n");
				}
			
				
			}else{
				throw new UserNotFoundException("\n*Usuário não existe*\n");
			}
		
		}catch(IncompleteDataException | UserNotFoundException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public void dashboard(){
		int input;
		boolean end = false;
		Scanner scanner = new Scanner(System.in);
		
		while(!end){
			System.out.println("\n1-Editar perfil\n" + "2-Add amigo\n" + "3-Verificar solicitações de amizade\n" 
			+ "4-Enviar mensagem\n" + "5-Ler mensagens\n" + "6-Criar comunidade\n" + "7-Entrar em uma comunidade\n" + "8-recuperar informações\n"
					+ "9-Remoção de conta\n" + "10-Sair\n");
			
			try{
				scanner = new Scanner(System.in);
				input = scanner.nextInt();
				
				if(input == 1){
					this.edit();
				}else if(input == 2){
					this.addFriend();
				}else if(input == 10){
					end = true;
				}else if(input == 3){
					this.confirmRequest();
				}else if(input == 4){
					this.sendMessage();
				}else if(input == 5){
					this.printMessage();
				}else if(input == 6){
					this.createCommunity();
				}else if(input == 7){
					this.addMenber();
				}else if(input == 8){
					this.recoveringData();
				}else if(input == 9){
					this.researchersDataBase.remove(CurrentResearcher.getLogin());
					this.CurrentResearcher = null;
					end = true;
				}else{
					throw new InputMismatchException();
				}
			
			
			}catch(InputMismatchException e){
				System.out.println("\n*Digite uma opção válida*\n");
			} catch (UserNotFoundException | IncompleteDataException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public void edit(){
		Scanner scanner = null;
		int input;
		System.out.println("\n1-Editar nome\n" + "2-Editar login\n" + "3-Editar senha\n" + "4-Remover amigo\n" + 
		"5-remover comunidade\n");
		
		try{
			scanner = new Scanner(System.in);
			input = scanner.nextInt();
			
			if(input == 1){
				String name;
				Scanner scanner2 = new Scanner(System.in);
				
				System.out.println("\nDigite o novo nome\n");
				name = scanner2.nextLine();
				
				if(name.length() == 0){
					throw new IncompleteDataException("\n*Não foi possível modificar o nome*\n*você não digitou nada*\n");
				}
				
				this.CurrentResearcher.setName(name);
				
			}else if(input ==2 ){
				String login;
				Scanner scanner2 = new Scanner(System.in);
				
				System.out.println("Digite o novo login");
				login = scanner2.nextLine();
				
				if(login.length() == 0){
					throw new IncompleteDataException("\n*Não foi possível modificar o login*\n*você não digitou nada*\n");
				}
				
				if(this.researcherExist(login)){
					throw new ResearcherExistException("\n*Login já existe*\n");
				}
				
				this.CurrentResearcher.setLogin(login);
				
				
			}else if(input == 3){
				String password;
				Scanner scanner2 = new Scanner(System.in);
				
				System.out.println("Digite o novo login");
				password = scanner2.nextLine();
				
				if(password.length() == 0){
					throw new IncompleteDataException("\n*Não foi possível modificar a senha*\n*você não digitou nada*\n");
				}
				this.CurrentResearcher.setPassword(password);
				
			}else if(input == 4){
				String login;
				boolean exist = false;
				Scanner scanner2 = new Scanner(System.in);
				
				System.out.println("\nDigite o login do amigo que deseja remover\n");
				login = scanner2.nextLine();
				
				for(int i = 0; i < this.CurrentResearcher.getFriends().size(); i++){
					if(this.CurrentResearcher.getFriends().get(i).getLogin().equals(login)){
						this.CurrentResearcher.removeFriend(login);
						exist = true;
						break;
					}
				}
				
				if(!exist){
					throw new UserNotFoundException("\n*Amigo não encontrado*\n");
				}
				
				
			}else if(input == 5){
				String name;
				boolean exist = false;
				Scanner scanner2 = new Scanner(System.in);
				
				System.out.println("\nDigite o nome da comunidade de deseja excluir\n");
				name = scanner2.nextLine();
				
				for(int i = 0; i < this.CurrentResearcher.getCommunities().size(); i++){
					if(this.CurrentResearcher.getCommunities().get(i).getName().equals(name)){
						this.CurrentResearcher.getCommunities().remove(i);
						exist = true;
						break;
					}
				}
				
				if(!exist){
					throw new UserNotFoundException("\nComunidade não encontrada\n");
				}
				
			}
		}catch(InputMismatchException e){
			System.out.println("\n*Digite uma opção válida*\n");
			
		}catch (ResearcherExistException | IncompleteDataException | UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public void addFriend() throws UserNotFoundException{
		String login;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nDigite o login do seu amigo\n");
		login = scanner.nextLine();
		
		if(this.researcherExist(login)){
			Message message = new Message();
			message.setSender(CurrentResearcher);
			message.setText("Solicitação de amizade");
			
			System.out.println("Solicitação de amizade enviada");
			
			this.researchersDataBase.search(login).getRequests().add(message);
		
		}else{
			throw new UserNotFoundException("\nUsuário não existe\n");
		}
		
	}
	public void confirmRequest(){
		for(int i = 0; i < this.CurrentResearcher.getRequests().size(); i++){
			System.out.println("|" + i +"|Solicitação de amizade de "
		+ this.CurrentResearcher.getRequests().get(i).getSender().getLogin() + "|");
			
		}
		
		int input;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Deseja confirmar ou rejeitar alguma soliciação?\n1-sim\n2-não");
		
		try{
			input = scanner.nextInt();
			
			if(!(input == 2 || input == 1)){
				throw new InputMismatchException();
			}
			
			if(input == 1){
				System.out.println("1-aceitar\n2-rejeitar");
				scanner =new Scanner(System.in);
				input = scanner.nextInt();
				
				if(!(input == 2 || input == 1)){
					throw new InputMismatchException();
				}
				
				if(input ==1){
					System.out.println("Escolha o número da solicitação");
					scanner =new Scanner(System.in);
					input = scanner.nextInt();
					
					if(input > (this.CurrentResearcher.getRequests().size() - 1)){
						throw new InputMismatchException();
					}
					
					this.CurrentResearcher.getFriends().add(this.CurrentResearcher.getRequests().get(input).getSender());
					this.CurrentResearcher.getRequests().get(input).getSender().getFriends().add(this.CurrentResearcher);
					this.CurrentResearcher.getRequests().remove(input);
					
				}else{
					this.CurrentResearcher.getRequests().remove(input);
				}
			}
		}catch(InputMismatchException e){
			System.out.println("Digite uma opção válida");
		}
		
	}
	public void sendMessage() throws UserNotFoundException, IncompleteDataException{
		String login, text, title;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nDigite o login do seu amigo\n");
		login = scanner.nextLine();
		
		if(this.researcherExist(login)){
			
			System.out.println("\nDigite sua mensagem\n");
			Scanner scanner2 = new Scanner(System.in);
			text = scanner2.nextLine();
			
			if(text.length() == 0){
				throw new IncompleteDataException("\n*Você não digitou nenhuma mensagem*\n");
			}
			
			System.out.println("\nDigite o título da mensagem\n");
			Scanner scanner3 = new Scanner(System.in);
			title = scanner3.nextLine();
			
			if(title.length() == 0){
				throw new IncompleteDataException("\n*Você não digitou nenhuma título*\n");
			}
			
			Message message = new Message();
			message.setSender(CurrentResearcher);
			
			message.setText(text);
			message.setTitle(title);
			
			System.out.println("\nMensagem enviada\n");
			
			this.researchersDataBase.search(login).getMessages().add(0, message);
		
		}else{
			throw new UserNotFoundException("\n*Usuário não existe*\n");
		}
	}
	public void printMessage() throws InputMismatchException{
		for(int i = 0; i < this.CurrentResearcher.getMessages().size(); i++){
			System.out.println("\n|"+ i +"|" + this.CurrentResearcher.getMessages().get(i).getSender().getLogin()
					+ " " + this.CurrentResearcher.getMessages().get(i).getTitle()+ " |\n");
		}
		if(this.CurrentResearcher.getMessages().size() != 0){
			Scanner scanner = new Scanner(System.in);
			int input;
			
			System.out.println("\nDigite o número da mensagem que você quer ler\n");
			input = scanner.nextInt();
			
			
			if(input > (this.CurrentResearcher.getMessages().size() - 1)){
				throw new InputMismatchException();
			}
			
			System.out.println(this.CurrentResearcher.getMessages().get(input).getText());
		
		}else{
			System.out.println("\n*Você não possui mensagens*\n");
		}
		
	}

	public void createCommunity(){
		String name, description;
		Scanner scanner;
		
		try{
			
			
			System.out.println("\nDigite o nome da comunidade\n");
			scanner = new Scanner(System.in);
			name = scanner.nextLine();
			
			if(name.length() == 0){
				throw new IncompleteDataException("\n*você não digitou nada*\n");
			}
			
			System.out.println("\nDigite a descrição da comunidade\n");
			scanner = new Scanner(System.in);
			description = scanner.nextLine();
			
			if(description.length() == 0){
				throw new IncompleteDataException("\n*você não digitou nada*\n");
			}
			
	
			Community community = new Community();
			community.setName(name);
			community.setDescription(description);
			community.getParticipants().add(0,this.CurrentResearcher);
			
			this.CurrentResearcher.getCommunities().add(community);
			
			this.communitiesDataBase.add(community);
		}catch(IncompleteDataException e){
			System.out.println(e.getMessage());
		}
		
	}
	public  void addMenber(){
		String name;
		Scanner scanner = new Scanner(System.in);
		
		try{
			System.out.println("\nDigite o nome da comunidade\n");
			name = scanner.nextLine();
			
			if(this.communityExist(name)){
				this.CurrentResearcher.getCommunities().add(this.communitiesDataBase.search(name));
				System.out.println("Comunidade encontrada");
				this.communitiesDataBase.search(name).getParticipants().add(this.CurrentResearcher);
			}else{
				throw new CommunityNotFoundException("\n*Comunidade não encontrada*\n");
			}
		}catch(CommunityNotFoundException e){
			System.out.println(e.getMessage());
		}
		
	}
	public boolean communityExist(String name){
		if(this.communitiesDataBase.search(name) == null){
			return false;
		}
		return true;
	}
	public void recoveringData(){
		
		
		System.out.println("\nInformações pessoais:\n\n" + "|Nome -> " + this.CurrentResearcher.getName() + 
				" | Login-> " + this.CurrentResearcher.getLogin() + " |\n");
		
		System.out.println("\nComunidades:\n");
		for(int i = 0; i < this.CurrentResearcher.getCommunities().size(); i++){
			System.out.println("| Nome -> " + this.CurrentResearcher.getCommunities().get(i).getName() + 
					"|| Descrição -> " + this.CurrentResearcher.getCommunities().get(i).getDescription()+ " |\n");
		}
		
		System.out.println("Amigos:\n");
		
		for(int i = 0; i < this.CurrentResearcher.getFriends().size(); i++){
			System.out.println( "\n| Nome -> " + this.CurrentResearcher.getFriends().get(i).getName() + 
					" | Login-> " + this.CurrentResearcher.getFriends().get(i).getLogin() + " |\n");
			
		}
		
		System.out.println("\nMensagens:\n");
		
		for(int i = 0; i < this.CurrentResearcher.getMessages().size(); i++){
			System.out.println("\n| Título -> " + this.CurrentResearcher.getMessages().get(i).getTitle() 
					+ "|| Nome -> " + this.CurrentResearcher.getMessages().get(i).getSender().getName() + "|\n");
		}
		
		
	}
	
}

