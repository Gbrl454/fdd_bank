export class Util {
        transformarStringParaVetor(
          json: string
        ): { campo: string; mensagem: string }[] {
          try {
            const entrada = JSON.parse(json);
            const resultado: { campo: string; mensagem: string }[] = [];
      
            entrada.forEach((item: { campo: any; mensagem: any }) => {
              resultado.push({ campo: item.campo, mensagem: item.mensagem });
            });
      
            return resultado;
          } catch (error) {
            throw new Error();
          }
        }
      }
      