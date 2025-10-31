package br.edu.ifpb.pweb2.sessoes.model;
/** Esta classe associa um item a um carrinho de compras. O item
 * s� est� no carrinho se estiver contido num objeto desta classe.
 */

public class ItemCarrinho {
  private Item item;
  private int numItens;
  
  public ItemCarrinho() {
	  
  }

  public ItemCarrinho(Item item) {
    setItem(item);
    setNumItens(1);
  }

  public Item getItem() {
    return(item);
  }

  protected void setItem(Item item) {
    this.item = item;
  }

  public String getId() {
    return(getItem().getId());
  }

  public String getDescricaoCurta() {
    return(getItem().getDescricaoCurta());
  }

  public String getDescricaoLonga() {
    return(getItem().getDescricaoLonga());
  }

  public double getPrecoUnitario() {
    return(getItem().getPreco());
  }
  
  public int getNumItens() {
    return(numItens);
  }

  public void setNumItens(int n) {
    this.numItens = n;
  }

  public void incrementeNumItens() {
    setNumItens(getNumItens() + 1);
  }

  public void cancelarPedido() {
    setNumItens(0);
  }

  public double getPrecoTotal() {
    return(getNumItens() * getPrecoUnitario());
  }
}
