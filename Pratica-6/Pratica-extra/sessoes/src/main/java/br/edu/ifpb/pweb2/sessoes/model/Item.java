package br.edu.ifpb.pweb2.sessoes.model;
/** Descreve um item de cat�logo da livraria.
 * 
 */

public class Item {
  private String id;
  private String descricaoCurta;
  private String descricaoLonga;
  private double preco;

  public Item(String itemID, String curta,
              String longa, double preco) {
    setId(itemID);
    setDescricaoCurta(curta);
    setDescricaoLonga(longa);
    setPreco(preco);
  }
    
  public String getId() {
    return(id);
  }

  protected void setId(String itemID) {
    this.id = itemID;
  }

  public String getDescricaoCurta() {
    return(descricaoCurta);
  }

  protected void setDescricaoCurta(String descricao) {
    this.descricaoCurta = descricao;
  }

  public String getDescricaoLonga() {
    return(descricaoLonga);
  }

  protected void setDescricaoLonga(String descricao) {
    this.descricaoLonga = descricao;
  }

  public double getPreco() {
    return(preco);
  }

  protected void setPreco(double preco) {
    this.preco = preco;
  }
}
  
