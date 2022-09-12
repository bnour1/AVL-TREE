package avltree;

public class AVLTree<T extends Comparable<T>> {

    public TreeNode<T> raiz;

    int getHeight(TreeNode<T> No) {
        if (No == null)
            return 0;

        return No.getHeight();
    }

    private TreeNode<T> insercaoRecursiva(TreeNode<T> node, T info) {
        if (node == null) {
            return (new TreeNode<T>(info));
        }
        if (info.compareTo(node.getInfo()) < 0) {
            node.setEsq(inserirNo(node.getEsq(), info));
        } else if (info.compareTo(node.getInfo()) > 0) {
            node.setDir(inserirNo(node.getDir(), info));
        } else {
            return node;
        } // Duplicate infos not allowed
        return node;
    }

    private void atualizarAlturaNo(TreeNode<T> node) {
        node.setHeight(1 + maiorAltura(getHeight(node.getEsq()),
                getHeight(node.getDir())));
    }

    public TreeNode<T> inserirNo(TreeNode<T> node, T info) {
        node = insercaoRecursiva(node, info); // insere normalmente com recursão
        atualizarAlturaNo(node); // Atualiza a altura do no anterior
        int fatorbalanceamento = getBalance(node); // pega fator de balanceamento do no anterior ao inserido, e ve se desbalanceou
        this.raiz = node;
        return rebalancear(fatorbalanceamento, node, info); // Rebalanceia a arvore
    }

    TreeNode<T> rebalancear(int fatorbalanceamento, TreeNode<T> node, T info) {
        TreeNode<T> aux;
        if (fatorbalanceamento < -1 && info.compareTo(node.getEsq().getInfo()) < 0) { // Rotacao LL
            System.out.println("Realizando rotação LL apartir do nó "+ node.getInfo());
            aux = rightRotate(node);
            this.raiz = aux;
            return aux;
        }if(fatorbalanceamento > 1 && info.compareTo(node.getDir().getInfo()) > 0){ // Rotacao RR
            System.out.println("Realizando rotação RR apartir do nó "+ node.getInfo());
            aux = leftRotate(node);
            this.raiz = aux;
            return aux;
        }if (fatorbalanceamento < -1 && info.compareTo(node.getEsq().getInfo()) > 0){ // Rotacao LR
            System.out.println("Realizando rotação LR apartir do nó "+ node.getInfo());
            aux = leftRotate(node.getEsq());
            node.setEsq(aux);
            aux = rightRotate(node);
            this.raiz = aux;
            return aux;
        }if (fatorbalanceamento > 1 && info.compareTo(node.getDir().getInfo()) < 0) { // Rotacao RL
            System.out.println("Realizando rotação RL apartir do nó "+ node.getInfo());
            aux = rightRotate(node.getDir());
            node.setDir(aux);
            aux = leftRotate(node);
            this.raiz = aux;
            return aux;
        }
        return node;
    }

    public void ExibirOrdemArvore(TreeNode<T> aux) {
        if (aux != null) {
            System.out.print(aux.getInfo() + " ");
            ExibirOrdemArvore(aux.getEsq());
            ExibirOrdemArvore(aux.getDir());
        }
    }

    int maiorAltura(int a, int b) {
        if (a > b){
            return a;
        }else{
            return b;
        }
    }

    TreeNode<T> rightRotate(TreeNode<T> y) {
        TreeNode<T> x = y.getEsq();
        TreeNode<T> T2 = x.getDir();
        x.setDir(y);
        y.setEsq(T2);
        y.setHeight(maiorAltura(getHeight(y.getEsq()), getHeight(y.getDir())) + 1);
        x.setHeight(maiorAltura(getHeight(x.getEsq()), getHeight(x.getDir())) + 1);

        return x;
    }

    TreeNode<T> leftRotate(TreeNode<T> x) {
        TreeNode<T> y = x.getDir();
        TreeNode<T> T2 = y.getEsq();
        y.setEsq(x);
        x.setDir(T2);
        x.setHeight(maiorAltura(getHeight(x.getEsq()), getHeight(x.getDir())) + 1);
        y.setHeight(maiorAltura(getHeight(y.getEsq()), getHeight(y.getDir())) + 1);

        return y;
    }

    int getBalance(TreeNode<T> N) {
        if (N == null)
            return 0;
        return getHeight(N.getDir()) - getHeight(N.getEsq());
    }

    public void ExibirEmOrdem(TreeNode<T> aux) {
        if (aux == null) {
            return;
        } else {
            ExibirEmOrdem(aux.getEsq());
            System.out.print(aux.getInfo()+" ");
            ExibirEmOrdem(aux.getDir());
        }
    }

    private TreeNode<T> removerNoRaiz(TreeNode<T> raiz){
        TreeNode<T> aux;
        if ((raiz.getEsq() == null) || (raiz.getDir() == null)) { // se o nó só tiver 1 filho ou nenhum filho
            aux = null;
            if (aux == raiz.getEsq())
                aux = raiz.getDir();
            else
                aux = raiz.getEsq();
            // se não tiver filho algum
            if (aux == null) {
                aux = raiz;
                raiz = null;
            } else // se só tiver um filho
                raiz = aux; // salva o conteudo do filho
        } else { // maior nó a esquerda será a raiz
            aux = getMenorNo(raiz.getDir());
            // seta a nova raiz
            raiz.setInfo(aux.getInfo());
            // remove no antigo
            raiz.setDir(removerNo(raiz.getDir(), aux.getInfo()));
        }

        return raiz;
    }

    public TreeNode<T> removerNo(TreeNode<T> raiz, T info) {
        if (raiz == null){
            System.out.println("A arvore está vazia.");
            return raiz;
        }
        if (info.compareTo(raiz.getInfo()) < 0){ // se o valor for menor que a raiz, esta localizado à esquerda dela
            raiz.setEsq(removerNo(raiz.getEsq(), info));
        }else if (info.compareTo(raiz.getInfo()) > 0) // se o valor for menor que a raiz, esta localizado à direita dela
            raiz.setDir(removerNo(raiz.getDir(), info));
        else { // se o valor não está ali, o que precisa ser deletado está na raiz
            raiz = removerNoRaiz(raiz);
        }

        this.raiz = raiz;
        if (raiz == null){ // se a arvore não possui mais raiz, finaliza.
            return raiz;
        }

        // Atualiza altura do nó raiz atual
        raiz.setHeight(maiorAltura(getHeight(raiz.getEsq()), getHeight(raiz.getDir())) + 1);

        //verificar o fator de balanceamento (após remoção)
        int fatorbal = getBalance(raiz);

        // If this node becomes unbalanced, then there are 4 cases

        return rebalancearRemocao(fatorbal, raiz);
    }

    private TreeNode<T> rebalancearRemocao(int fatorbal, TreeNode<T> raiz){
            if (fatorbal < -1 && getBalance(raiz.getEsq()) >= 0){ // Rotacao LL
                System.out.println("Realizando rotação LL apartir do nó, "+raiz.getInfo());
                return rightRotate(raiz);
            }
            if (fatorbal < -1 && getBalance(raiz.getEsq()) < 0) { // Rotacao RL
                System.out.println("Realizando rotação RL apartir do nó, "+raiz.getInfo());
                raiz.setEsq(leftRotate(raiz.getEsq()));
            }
            if (fatorbal > 1 && getBalance(raiz.getDir()) <= 0){ // Rotacao RR
                System.out.println("Realizando rotação RR apartir do nó, "+raiz.getInfo());
                return leftRotate(raiz);
            }
            if (fatorbal > 1 && getBalance(raiz.getDir()) > 0) { // Rotacao  LR
                System.out.println("Realizando rotação LR apartir do nó, "+raiz.getInfo());
                raiz.setDir(rightRotate(raiz.getDir()));
                return leftRotate(raiz);
            }
            return raiz;
    }

    // Metodo para encontrar e retornar o menor nó a esquerda
    TreeNode<T> getMenorNo(TreeNode<T> node) {
        TreeNode<T> aux = node;
        while (aux.getEsq() != null)
            aux = aux.getEsq();
        return aux;
    }

}
