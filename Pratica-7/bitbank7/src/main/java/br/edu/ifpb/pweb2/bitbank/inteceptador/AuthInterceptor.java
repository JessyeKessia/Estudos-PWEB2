package br.edu.ifpb.pweb2.bitbank.inteceptador;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception {
        // pega a sessão atual, se tiver
        HttpSession httpSession = request.getSession(false);

        // se a sessao for diferente de nulo
        if (httpSession != null) {
            // pega e salva o atributo correntista dentro da sessao como usuario
            Correntista usuario  = (Correntista) httpSession.getAttribute("usuario");

            if (usuario != null) {
                String contextPath = request.getContextPath();
                String path = request.getRequestURI();

                // remove o context path da requisição
                String relativePath = path.substring(contextPath.length());

                // verifica se a URL acessada começa com "/correntista" "/contas"
                boolean requerAdmin = relativePath.startsWith("/correntistas") || relativePath.startsWith("/contas");

                // se exige admin, verifica se o user é admin
                if (requerAdmin && !usuario.isAdmin()) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso negado");
                    // n é admin n tem permissao de acesso aos recursos de correntistas e contas
                    return false;
                }
                // logado com permissão
                return true; 
            }
        }
         // caso não tenha sessão, o user tbm não tem acesso
            // pega o caminho base (o nome da aplicacao)
            String baseURL = request.getContextPath();
            // redirecionada para a página de autenticação
            String paginaLogin = baseURL + "/auth";
            response.sendRedirect(response.encodeRedirectURL(paginaLogin));
            // bloqueia a entrada
            return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Sem uso
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        // Sem uso
    }
}
