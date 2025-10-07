# Desafio_POO_equipe_03
Este projeto é um aplicativo Java com interface gráfica (SWT) para gerenciar notícias. A aplicação permite ao usuário adicionar, editar, excluir e salvar notícias em um arquivo CSV, além de exibir o número total de itens cadastrados.
---------------------------------------------------------------------------------------------------
2. Estrutura Principal:
A classe principal é chamada `layout_teste`, pertencente ao pacote `layout`. Ela contém os métodos principais e a configuração da janela gráfica.

3. Interface Gráfica (SWT):
A aplicação utiliza os seguintes componentes gráficos do SWT:
- `Shell`: Janela principal da aplicação.
- `Label`: Exibe textos fixos como títulos e legendas.
- `Button`: Botões de ação como 'Buscar', 'Adicionar', 'Editar', 'Excluir' e 'Salvar'.
- `Text`: Campos de entrada de texto para buscar e inserir notícias.
- `List`: Exibe a lista de notícias cadastradas.
- `Combo`: Menu suspenso para selecionar a categoria da notícia.
- `DateTime`: Exibe a data e hora atuais.
- `MessageBox`: Mostra mensagens de alerta, erro ou sucesso.

4. Funcionalidades Principais:

a) Adicionar notícia:
Permite inserir uma nova notícia associada a uma categoria. O texto é validado para garantir que a categoria e o campo de texto não estejam vazios. A notícia é então exibida na lista no formato: [CATEGORIA] - Notícia.

b) Editar notícia:
Permite alterar o texto e/ou categoria de uma notícia já existente. O item selecionado na lista é substituído pela nova versão.

c) Excluir notícia:
Remove o item selecionado da lista de notícias. Se nenhum item estiver selecionado, o sistema exibe um aviso.

d) Salvar em CSV:

A função `salvarParaCSV()` exporta todas as notícias da lista para um arquivo chamado `noticias_cnn.csv`, com as colunas 'Categoria' e 'Notícia'. O arquivo é criado no mesmo diretório de execução.

5. Contador de Itens e Status:

O programa exibe o número total de notícias cadastradas e mantém uma etiqueta de status mostrando 'Sistema estável'. O contador é atualizado automaticamente sempre que um item é adicionado ou removido.

6. Execução do Programa:

A execução começa no método `main`, que inicializa o display SWT e abre o shell (janela). O loop principal (`while (!shell.isDisposed())`) mantém a aplicação aberta e responsiva até que o usuário feche a janela.

7. Conclusão:

Este programa demonstra o uso prático da biblioteca SWT em Java para criação de interfaces gráficas interativas. Ele também mostra boas práticas de manipulação de eventos e escrita de arquivos CSV, tornando-se um exemplo didático de CRUD simples (Create, Read, Update, Delete).









































