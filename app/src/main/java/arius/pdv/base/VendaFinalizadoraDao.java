package arius.pdv.base;

import java.sql.SQLException;

import arius.pdv.core.AppContext;
import arius.pdv.core.AriusResultSet;
import arius.pdv.core.GenericDao;

public class VendaFinalizadoraDao extends GenericDao<VendaFinalizadora> {

	private VendaDao vendaDao;
	private FinalizadoraDao finalizadoraDao;
	
	@Override
	public void init() {
		tableName = "vendas_finalizadoras";
	fields = new String[]{"venda_id","finalizadora_id","valor","desconto","juro"};
		
		vendaDao = AppContext.get().getDao(VendaDao.class);
		finalizadoraDao = AppContext.get().getDao(FinalizadoraDao.class);
	}

	@Override
	protected void resultSetToEntity(AriusResultSet resultSet, VendaFinalizadora entity) throws SQLException {
		// campo direto
		entity.setId(resultSet.getInt("id"));
		entity.setVenda(vendaDao.find(resultSet.getInt("venda_id")));
		entity.setFinalizadora(finalizadoraDao.find(resultSet.getInt("finalizadora_id")));
		entity.setValor(resultSet.getDouble("valor"));
		entity.setDesconto(resultSet.getDouble("desconto"));
		entity.setJuro(resultSet.getDouble("juro"));
	}
	
	@Override
	public void bindFields(VendaFinalizadora entity) throws SQLException {
		stInsertUpdate.setInt("venda_id", entity.getVenda().getId());
		stInsertUpdate.setInt("finalizadora_id", entity.getFinalizadora().getId());
		stInsertUpdate.setDouble("valor", entity.getValor());
		stInsertUpdate.setDouble("desconto", entity.getDesconto());
		stInsertUpdate.setDouble("juro", entity.getJuro());
		
		//Deixar sempre por ultimo este campo, pois é usado no momento de montar a condição para o update
		if (!stInsertUpdate.getInsert()){
			stInsertUpdate.setInt("id", entity.getId());
		}			
	}
}
