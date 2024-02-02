package tacos;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.Data;
import tacos.data.OrderRepository;

@Data
@Service
public class OrderAdminService {

	private OrderRepository orderRepo;
	
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAllOrders() {
		 orderRepo.deleteAll();
	}
	
	@PostAuthorize("hasRole('ADMIN') || "+"returnObject.users.username == authentication.name")
	public TacoOrder getOrder(long id) {
		return orderRepo.findById(id).get();
	}
}
