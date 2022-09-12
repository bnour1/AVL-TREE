import avltree.*;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        Scanner input = new Scanner(System.in);
        int op, info;
        do{
            menu();
            op=input.nextInt();
            switch(op){
                case 1:
                System.out.print("\nInsira o numero: ");
                info = input.nextInt();
                System.out.println("\ninserindo nó com valor, "+info);
                tree.inserirNo(tree.raiz, info);
                break;
                case 2:
                info = input.nextInt();
                tree.removerNo(tree.raiz, info);
                System.out.println("\nRemovendo nó com valor, "+info);
                break;
            }
            System.out.println("\nExibição na ordem da arvore");
            tree.ExibirOrdemArvore(tree.raiz);
            System.out.println("\nExibição em modo crescente");
            tree.ExibirEmOrdem(tree.raiz);
            System.out.println("\nVizualização");
            PrintTree.printBinaryTree(tree.raiz);
        }while(op!=0);
    }
    static void menu(){
        System.out.println("\n1 - Para inserir");
        System.out.println("2 - Para deletar");
        System.out.println("0- Para sair");
    }
}
