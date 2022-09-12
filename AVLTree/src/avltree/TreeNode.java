package avltree;

class TreeNode<T extends Comparable<T>> {
    private TreeNode<T> esq;
    private T info;
    private TreeNode<T> dir;
    private int height;

    TreeNode(T valor) {
        this.info = valor;
        this.height = 1;
    }

    void setInfo(T valor) {
        this.info = valor;
    }

    void setEsq(TreeNode<T> novaEsq) {
        this.esq = novaEsq;
    }

    void setHeight(int h){
        this.height = h;
    }

    void setDir(TreeNode<T> novaDir) {
        this.dir = novaDir;
    }

    T getInfo() {
        return this.info;
    }

    int getHeight(){
        return this.height;
    }

    TreeNode<T> getEsq() {
        return this.esq;
    }

    TreeNode<T> getDir() {
        return this.dir;
    } 

}