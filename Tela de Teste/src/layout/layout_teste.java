package layout;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class layout_teste {
	private static Text text;
	private static Text text_1;
	private static Label lblContadorItens;

	private static void atualizarContador(List list) {
		lblContadorItens.setText("Total de Itens: " + list.getItemCount());
	}
    
    private static void salvarParaCSV(Shell shell, List list) {
        String nomeArquivo = "noticias_cnn.csv";
        String cabecalho = "Categoria;Noticia"; 
        
           try (
            FileWriter fileWriter = new FileWriter(nomeArquivo);
            PrintWriter printWriter = new PrintWriter(fileWriter)
        ) {       
            printWriter.println(cabecalho);
            
            String[] itens = list.getItems();
            for (String itemCompleto : itens) {              
                int indiceSeparador = itemCompleto.indexOf("] - ");
                
                if (indiceSeparador != -1) {             
                    String categoria = itemCompleto.substring(1, indiceSeparador).trim();        
        
                    String noticia = itemCompleto.substring(indiceSeparador + 4).trim();
                    
         
                    printWriter.println(categoria + ";" + noticia);
                }
            }
            
           
            MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
            mb.setText("Sucesso");
            mb.setMessage("Dados salvos com sucesso em: " + nomeArquivo);
            mb.open();

        } catch (IOException ex) {
    
            MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            mb.setText("Erro de I/O");
            mb.setMessage("Erro ao salvar o arquivo: " + ex.getMessage());
            mb.open();
        }
    }
  

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(518, 396);
		shell.setText("SWT Application");

		Button btnBuscar = new Button(shell, SWT.NONE);
		btnBuscar.setBounds(256, 56, 75, 25);
		btnBuscar.setText("Buscar");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(108, 58, 142, 21);

		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(337, 56, 75, 25);
		btnLimpar.setText("Limpar");

		btnLimpar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
			}
		});

		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 34, 502, 2);

		Label lblBuscar = new Label(shell, SWT.NONE);
		lblBuscar.setBounds(47, 61, 55, 15);
		lblBuscar.setText("Buscar:");

		Label lblGerenciadorDeNotcias_1 = new Label(shell, SWT.NONE);
		lblGerenciadorDeNotcias_1.setBounds(152, 10, 193, 15);
		lblGerenciadorDeNotcias_1.setText("GERENCIADOR DE NOTÍCIAS - CNN");

		Label lblNoticias = new Label(shell, SWT.NONE);
		lblNoticias.setBounds(47, 87, 55, 15);
		lblNoticias.setText("Notícias:");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(144, 211, 268, 21);

		List list = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		list.setBounds(47, 108, 365, 86);

		lblContadorItens = new Label(shell, SWT.NONE);
		lblContadorItens.setBounds(47, 275, 150, 15);
		atualizarContador(list);

		Button btnAdicionar = new Button(shell, SWT.NONE);
		btnAdicionar.setBounds(175, 238, 75, 25);
		btnAdicionar.setText("Adicionar");

		Button btnEditar = new Button(shell, SWT.NONE);
		btnEditar.setBounds(256, 238, 75, 25);
		btnEditar.setText("Editar");

		Button btnExcluir = new Button(shell, SWT.NONE);
		btnExcluir.setBounds(337, 238, 75, 25);
		btnExcluir.setText("Excluir");

		Label lblStatusSistemaEstavel = new Label(shell, SWT.NONE);
		lblStatusSistemaEstavel.setBounds(47, 312, 162, 15);
		lblStatusSistemaEstavel.setText("Status: Sistema estavel");

		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(332, 303, 80, 24);

		Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] {"GERAL", "POLITICA", "ECONOMIA", "ESPORTES", "TECNOLOGIA", "ENTRETENIMENTO"});
		combo.setBounds(47, 209, 91, 23);
		combo.setText("CATEGORIA");
		
		
		Button btnSalvar = new Button(shell, SWT.NONE);
		btnSalvar.setBounds(418, 56, 75, 25); 
		btnSalvar.setText("SALVAR");

        
        btnSalvar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                salvarParaCSV(shell, list);
            }
        });
       

			btnAdicionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String categoria = combo.getText();
				String novoItem = text_1.getText().trim();

				if (categoria.equals("CATEGORIA") || categoria.isEmpty()) {
					MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					mb.setText("Aviso");
					mb.setMessage("Selecione uma categoria válida antes de adicionar.");
					mb.open();
				} else if (!novoItem.isEmpty()) {
					String itemCompleto = "[" + categoria + "] - " + novoItem;
					list.add(itemCompleto);
					text_1.setText("");
					atualizarContador(list);
				} else {
					MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					mb.setText("Aviso");
					mb.setMessage("O campo de inserção não pode estar vazio.");
					mb.open();
				}
			}
		});


			btnEditar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = list.getSelectionIndex();
				String categoria = combo.getText(); 
				String novoValor = text_1.getText().trim();

				if (index == -1) {
					MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					mb.setText("Aviso");
					mb.setMessage("Selecione um item da lista para editar.");
					mb.open();
				} else if (novoValor.isEmpty()) {
					MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					mb.setText("Aviso");
					mb.setMessage("O campo de inserção não pode estar vazio.");
					mb.open();
				} else if (categoria.equals("CATEGORIA") || categoria.isEmpty()) {
					MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					mb.setText("Aviso");
					mb.setMessage("Selecione uma categoria válida para a edição.");
					mb.open();
				} else {
					String itemCompleto = "[" + categoria + "] - " + novoValor;

					list.remove(index);
					list.add(itemCompleto, index); 
					list.setSelection(index);
					text_1.setText("");
				}
			}
		});


		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = list.getSelectionIndex();
				if (index != -1) {
					list.remove(index);
					text_1.setText("");
					atualizarContador(list);
				} else {
					MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					mb.setText("Aviso");
					mb.setMessage("Selecione um item da lista para excluir.");
					mb.open();
				}
			}
		});


		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] selection = list.getSelection();
				if (selection.length > 0) {
					String itemCompleto = selection[0];
					
					int indiceSeparador = itemCompleto.indexOf("] - ");
					
					if (indiceSeparador != -1) {
						String textoNoticia = itemCompleto.substring(indiceSeparador + 4);
						text_1.setText(textoNoticia);
						
						String categoria = itemCompleto.substring(1, indiceSeparador);
						combo.setText(categoria); 
					} else {
						text_1.setText(itemCompleto);
						combo.setText("CATEGORIA"); 
					}
				}
			}
		});

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		} 
	}
}