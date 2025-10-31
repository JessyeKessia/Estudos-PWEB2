package br.edu.ifpb.pweb2.sessoes.model;
import java.util.*;

public class Carrinho {
	private List<ItemCarrinho> itens;

	/** Cria um carrinho vazio. */

	public Carrinho() {
		itens = new ArrayList<ItemCarrinho>();
	}

	/** Retorna o vetor de itens do carrinho.
	 */

	public List<ItemCarrinho> getItens() {
		return (itens);
	}

	/** Verifica se j� existe um item deste no carrinho. Se
	 * existir, aumenta a quantidade dele. Se n�o, cria um novo
	 * item no carrinho
	 */

	public synchronized void adicioneItem(String itemID) {
		ItemCarrinho item;
		// Procura por um item j� presente e aumenta a quanidade
		for (int i = 0; i < itens.size(); i++) {
			item = (ItemCarrinho) itens.get(i);
			if (item.getId().equals(itemID)) {
				item.incrementeNumItens();
				return;
			}
		}
		// Cria um novo item
		ItemCarrinho novoItem = new ItemCarrinho(Catalogo.getItem(itemID));
		itens.add(novoItem);
	}

	/** Procure por item indicado no carrinho e define a quantidade
	 * de itens. Se quantidade for 0 ou negativo, retira do carrinho.
	 * Se for positiva, aumenta a quantidade no carrinho.
	 */

	public synchronized void setQtdeItens(String itemID, int quantidade) {
		ItemCarrinho item;
		for (int i = 0; i < itens.size(); i++) {
			item = (ItemCarrinho) itens.get(i);
			if (item.getId().equals(itemID)) {
				if (quantidade <= 0) {
					itens.remove(i);
				} else {
					item.setNumItens(quantidade);
				}
				return;
			}
		}
		ItemCarrinho novoItem = new ItemCarrinho(Catalogo.getItem(itemID));
		itens.add(novoItem);
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}
}
