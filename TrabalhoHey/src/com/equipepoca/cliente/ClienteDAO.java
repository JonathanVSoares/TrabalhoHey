package com.equipepoca.cliente;

import java.util.List;

public interface ClienteDAO {
	public void incluir(Cliente cliente);
	public void atualizar(Cliente cliente);
	public void excluir(Cliente cliente);
	public List<Cliente> listarClientes();
}
