package com.example.prueba.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.prueba.modelo.dao.CategoriaDAO;
import com.example.prueba.modelo.dao.ClienteDAO;
import com.example.prueba.R;
import com.example.prueba.modelo.dto.Categoria;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private EditText editTextNumber;
    private Button buttonChange;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;
    private ListView catListado;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Inicializa las vistas
        editTextNumber = root.findViewById(R.id.editTextNumber);
        buttonChange = root.findViewById(R.id.buttonChange);
        listView = root.findViewById(R.id.listView); // Asegúrate de que el ID coincida

        // Inicializa la lista y el adaptador
        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // Configura el botón
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia el valor en el EditText y lo agrega a la lista
                String newValue = "1"; // Cambiamos siempre a 1
                editTextNumber.setText(newValue);
                items.add(newValue); // Agrega el nuevo valor a la lista
                adapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
            }
        });

        return root;
    }

    private void listar() {
        CategoriaDAO cDAO = new CategoriaDAO(getActivity());  // Instancia del DAO para acceder a la base de datos
        List<Categoria> lista = cDAO.getList();  // Obtiene la lista de categorías

        // Crea un adaptador para mostrar las categorías en el ListView
        ArrayAdapter<Categoria> adp = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                lista
        );

        // Establece el adaptador en el ListView
        catListado.setAdapter(adp);
    }

}
